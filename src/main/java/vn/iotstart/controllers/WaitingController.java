package vn.iotstart.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstart.models.UserModel;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);  // Không tạo session mới nếu không tồn tại
        if (session != null && session.getAttribute("account") != null) {
            UserModel u = (UserModel) session.getAttribute("account");
            req.setAttribute("username", u.getUsername());

            System.out.println("User trong session: " + u.getUsername());  // Kiểm tra session

            if (u.getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath() + "/views/admin/home.jsp");
            } else if (u.getRoleid() == 3) {
                resp.sendRedirect(req.getContextPath() + "/views/manager/home.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/home.jsp");
            }
        } else {
            System.out.println("Session không tồn tại, chuyển hướng về trang login.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
