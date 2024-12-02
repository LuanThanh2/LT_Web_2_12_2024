<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
</head>
<body>
    <h2>Quên mật khẩu</h2>
    <form action="${pageContext.request.contextPath}/forgotPassword" method="post">
        <label for="email">Nhập địa chỉ email của bạn:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        <button type="submit">Gửi yêu cầu</button>
    </form>
    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>
</body>
</html>
