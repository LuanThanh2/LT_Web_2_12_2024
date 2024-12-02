<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="jakarta.tags.core"%>
    <%@ taglib prefix = "fmt" uri="jakarta.tags.fmt"%>
    <%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/LT_Web_Baitap_16_09_2024/login" method="post">
<c:if test="${alert != null}">
    <h3 class="alert alert-danger">${alert}</h3>
</c:if>

  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <b>Bạn chưa có tài khoản?</b>
    <a href="${pageContext.request.contextPath}/register">
        <button type="button" class="registerbtn">Register</button>
    </a>
</div>

<div class="container" style="background-color:#f1f1f1">
    <b>Quên mật khẩu</b>
    <a href="${pageContext.request.contextPath}/forgotPassword">
        <button type="button" class="forgotPasswordbtn">forgotPassword</button>
    </a>
</div>
</form>
</body>
</html>