<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        /* Your CSS styles go here */
        nav {
            background-color: #f4f4f4;
            padding: 10px;
            text-align: center; /* Center the contents */
        }
        nav div {
            display: flex;
            justify-content: space-around;
        }
        nav a {
            text-decoration: none;
            color: #333;
            padding: 5px 10px;
        }
        nav a:hover {
            background-color: #ccc;
        }
        button {
            padding: 5px 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<%
    HttpSession session1 = request.getSession();
    String rollNumber = (String) session1.getAttribute("username");
%>

<nav>
    <div>
        <a href="CreateorUpdate.jsp">Update Account</a>
        <a href="deleteAccount.jsp">Delete Account</a>
        <a href="showData.jsp">My Details</a>
        <a href="viewAccounts.jsp">Show All Details</a>
    </div>
    <form action="Logout" method="post">
        <button type="submit">Logout</button>
    </form>
</nav>

</body>
</html>
