<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
</head>
<body>
    <h2>Đặt lại mật khẩu</h2>
    <form action="${pageContext.request.contextPath}/resetPassword" method="post">
        <label for="newPassword">Mật khẩu mới:</label><br>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        
        <label for="confirmPassword">Xác nhận mật khẩu:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
        
        <button type="submit">Đặt lại mật khẩu</button>
    </form>
</body>
</html>
