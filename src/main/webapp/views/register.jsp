<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<form action="/LT_Web_Baitap_16_09_2024/register" method="post">
    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter Email" name="email" required>

        <label for="fullname"><b>Full Name</b></label>
        <input type="text" placeholder="Enter Full Name" name="fullname" required>
        
        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        
        <label for="img"><b>Image URL</b></label>
        <input type="text" placeholder="Enter Image URL" name="img" required>

        <label for="phone"><b>Phone</b></label>
        <input type="text" placeholder="Enter Phone" name="phone" required>

        <label for="roleid"><b>Role ID</b></label>
        <input type="number" placeholder="Enter Role ID" name="roleid" required>

        <!--   
        <label for="CreateDate"><b>CreateDate</b></label>
        <input type="date" placeholder="Enter CreateDate" name="createdate" required>
        -->
        <button type="submit">Register</button>
    </div>
</form>
</body>
</html>
