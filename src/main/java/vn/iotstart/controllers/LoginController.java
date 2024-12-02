package vn.iotstart.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstart.services.impl.UserService;
import vn.iotstart.utils.Constant;
import vn.iotstart.models.UserModel;
import vn.iotstart.services.IUserService;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra session
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Kiểm tra cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.COOKIE_REMEMBER)) {
                    String username = cookie.getValue();
                    UserModel user = service.FindByUserName(username); // Tìm user từ username trong cookie
                    if (user != null) {
                        session = req.getSession(true);  // Tạo session mới nếu cần
                        session.setAttribute("account", user);  // Lưu thông tin user vào session
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                        return;
                    }
                }
            }
        }

        // Nếu không có session và cookie, hiển thị trang login
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy tham số từ view
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String remember = req.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);

        // Kiểm tra nếu tài khoản hoặc mật khẩu bị bỏ trống
        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Xử lý đăng nhập
        UserModel user = service.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);  // Tạo session mới
            session.setAttribute("account", user);  // Lưu thông tin người dùng vào session

            // Kiểm tra xem Remember Me có được chọn hay không
            if (isRememberMe) {
                saveRememberMe(resp, username);  // Lưu cookie nếu chọn Remember Me
            }

            // Đăng nhập thành công, chuyển hướng về trang chờ
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            // Đăng nhập thất bại, hiển thị thông báo lỗi
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    // Hàm lưu cookie Remember Me
    private void saveRememberMe(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 24 * 60 * 60);  // Giữ cookie trong 30 ngày
        resp.addCookie(cookie);
    }
}
