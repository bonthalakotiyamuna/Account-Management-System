package accountmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteAccount")
public class deleteAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve student ID from request parameter
        String studentId = request.getParameter("studentId");
        System.out.println(studentId);

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/accountdb";
        String dbUsername = "root";
        String dbPassword = "9490";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Prepare SQL statement to delete student by ID
            String sql = "DELETE FROM account WHERE account_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);

            // Execute the DELETE statement
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Deletion successful
                response.sendRedirect("deleteAccount.jsp?message=Account deleted successfully");
            } else {
                // Student with specified ID not found
                response.sendRedirect("error.jsp?error=Account not found");
            }
        } catch (SQLException e) {
            // Handle database-related errors
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Database error occurred");
        } finally {
            // Close database resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

