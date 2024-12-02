<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to My Web Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
        }

        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .message {
            color: red;
            margin-bottom: 20px;
        }

        .options {
            margin-top: 20px;
        }

        .options a {
            margin-right: 15px;
            font-size: 16px;
            color: #4CAF50;
        }

        .options a:hover {
            color: #45a049;
        }

    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to My Web Application!</h1>
        
        <!-- Kiểm tra nếu người dùng đã đăng nhập (kiểm tra session) -->
        <c:if test="${not empty sessionScope.account}">
            <p>Hello, ${sessionScope.account.username}! You are logged in.</p>
            
            <!-- Thêm các tùy chọn cho người dùng đã đăng nhập -->
            <div class="options">
                <p>Would you like to do next?</p>
                <a href="${pageContext.request.contextPath}/login" class="btn">View Profile</a>
            </div>
            
            <a href="${pageContext.request.contextPath}/logout" class="btn">Logout</a>
        </c:if>
        
        <!-- Nếu chưa đăng nhập, hiển thị link login -->
        <c:if test="${empty sessionScope.account}">
            <p class="message">You are not logged in. Please log in to access more features.</p>
            <a href="${pageContext.request.contextPath}/login" class="btn">Login</a>
        </c:if>
    </div>
</body>
</html>
