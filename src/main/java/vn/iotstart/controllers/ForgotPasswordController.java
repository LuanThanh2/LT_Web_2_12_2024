package vn.iotstart.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstart.models.UserModel;
import vn.iotstart.services.IUserService;
import vn.iotstart.services.impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = {"/forgotPassword"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    // Thêm phương thức doGet để hiển thị trang quên mật khẩu
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển tiếp người dùng đến trang forgotPassword.jsp
        req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        UserModel user = userService.findByEmail(email);
        if (user != null) {
            // Chuyển hướng người dùng đến trang nhập mật khẩu mới
            req.getSession().setAttribute("resetEmail", email); // Lưu email vào session
            resp.sendRedirect(req.getContextPath() + "/resetPassword");
        } else {
            // Nếu không tìm thấy email, hiển thị thông báo lỗi
            req.setAttribute("alert", "Email không tồn tại");
            req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
        }
    }
}
