package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证是否登陆 拦截器
 * Created by acer on 2017/7/10.
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if (session.getAttribute("isLogin").equals(true)) {
            chain.doFilter(req, resp);
            return;
        }

        response.sendRedirect("login-module/login-page");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
