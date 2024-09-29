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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNumber = request.getParameter("studentId");
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        String account_name = request.getParameter("account_name");
        int balance = Integer.parseInt(request.getParameter("balance"));

        String url = "jdbc:mysql://localhost:3306/accountdb";
        String username = "root";
        String password = "9490";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE account SET account_id=?, customer_id=?, account_name=?, balance=? WHERE account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, account_id);
            ps.setInt(2, customer_id);
            ps.setString(3, account_name);
            ps.setInt(4, balance);
            ps.setString(5, rollNumber);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("viewAccounts.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
