<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Kiểm tra session ngay từ đầu (Sử dụng trực tiếp session mà không cần khai báo lại)
    if (session == null || session.getAttribute("account") == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    // Ngăn lưu cache của trình duyệt
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ Manager </title>
</head>
<body>
    <!-- Nội dung trang -->
    <h2>Trang chủ của Manager</h2>

    <!-- Nút logout -->
    <form action="<%= request.getContextPath() %>/logout" method="get">
        <button type="submit">Logout</button>
    </form>

</body>
</html>
