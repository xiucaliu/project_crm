package filter;

import model.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "adminOnlyFilter",urlPatterns = {"/role","/job/add","/job/delete"})
public class AdminOnlyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter authentication đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");

        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        int role_id = user.getRoleId();
        if (role_id != 1) {
            resp.sendRedirect(req.getContextPath() + "/error");
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
