package vn.iotstart.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy session hiện tại, nếu không có session nào thì không tạo mới
        HttpSession session = req.getSession(false);

        // Kiểm tra nếu session tồn tại thì xóa session
        if (session != null) {
            session.invalidate(); // Xóa tất cả dữ liệu trong session
        }

        // Chuyển hướng người dùng về trang login với thông báo logout thành công
        resp.sendRedirect(req.getContextPath() + "/views/login.jsp?logout=true");
    }
}
