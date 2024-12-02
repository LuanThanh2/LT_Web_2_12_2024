package vn.iotstart.controllers;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstart.models.UserModel;
import vn.iotstart.services.IUserService;
import vn.iotstart.services.impl.UserService;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển hướng đến trang đăng ký
        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form đăng ký
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String images = req.getParameter("img");  // Lấy ảnh từ form
        int roleid = req.getParameter("roleid") != null && !req.getParameter("roleid").isEmpty() 
                     ? Integer.parseInt(req.getParameter("roleid")) 
                     : 1; // Lấy roleid từ form, nếu không nhập thì set giá trị mặc định là 0
                     
        // Tự động lấy ngày hiện tại làm ngày đăng ký
        Date createDate = new Date(System.currentTimeMillis());

        String alertMsg = "";

        // Kiểm tra các trường bắt buộc
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            alertMsg = "Username, Password và Email là bắt buộc.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra nếu người dùng đã tồn tại
        if (userService.FindByUserName(username) != null) {
            alertMsg = "Tài khoản đã tồn tại";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo đối tượng UserModel và thêm vào DB
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullname(fullname);  // Các trường có thể để trống
        user.setPhone(phone);        // Các trường có thể để trống
        user.setImages(images);      // Các trường có thể để trống
        user.setRoleid(roleid);      // Roleid có thể để trống hoặc giá trị mặc định
        user.setCreateDate(createDate);  // Sử dụng ngày hiện tại

        userService.insert(user);

        // Chuyển hướng đến trang login sau khi đăng ký thành công
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
