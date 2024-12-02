package vn.iotstart.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/views/admin/*", "/views/manager/*", "/views/home"}) // Áp dụng cho nhiều URL
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Thêm các header để vô hiệu hóa cache
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        // Lấy đường dẫn hiện tại và kiểm tra query string
        String path = req.getRequestURI();
        String queryString = req.getQueryString();

        // Kiểm tra nếu là trang login hoặc tài nguyên tĩnh, cho phép tiếp tục
        if (path.startsWith(req.getContextPath() + "/views/login.jsp") ||
            path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra nếu người dùng đã đăng xuất (logout)
        if (queryString != null && queryString.contains("logout=true")) {
            if (session != null) {
                session.invalidate();  // Hủy session nếu người dùng đã logout
            }
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        // Kiểm tra session và account (người dùng chưa đăng nhập)
        if (session == null || session.getAttribute("account") == null) {
            // Chuyển hướng về trang login nếu chưa đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
        } else {
            // Nếu đã đăng nhập, cho phép tiếp tục truy cập
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần thiết
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên khi filter bị xóa
    }
}
