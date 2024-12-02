package vn.iotstart.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstart.services.IUserService;
import vn.iotstart.services.impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = {"/resetPassword"})
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    // Thêm phương thức doGet để hiển thị trang đặt lại mật khẩu
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển tiếp người dùng đến trang resetPassword.jsp
        req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String email = (String) session.getAttribute("resetEmail");

        if (email == null) {
            resp.sendRedirect(req.getContextPath() + "/forgotPassword");
            return;
        }

        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (newPassword.equals(confirmPassword)) {
            // Cập nhật mật khẩu mới cho user
            userService.updatePassword(email, newPassword);
            session.removeAttribute("resetEmail");  // Xóa thông tin reset email sau khi thành công
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Mật khẩu không khớp");
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
        }
    }
}
