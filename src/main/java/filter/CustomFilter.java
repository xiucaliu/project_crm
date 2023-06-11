package filter;

import model.LoginDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "loginFilter",urlPatterns = {"/*"})
public class CustomFilter implements Filter {//javax

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sessionCheckLogin = req.getSession();
        LoginDto loginDto = (LoginDto) sessionCheckLogin.getAttribute("loginDto");
        System.out.println("day la dto login "+loginDto);
        // nếu ko vào trang loggin thì check xem đã có login Dto chưa,
        if (!req.getServletPath().startsWith("/login")) {
            if (loginDto != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}
