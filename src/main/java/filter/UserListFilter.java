package filter;
import model.Users;
import service.JobService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserListFilter implements Filter {
    JobService jobService = new JobService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter userInfo đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        int role_id = user.getRole_id();
        boolean test = req.getServletPath().endsWith("user");
        System.out.println(test + "ket thuc voi /user?");
        if (role_id != 1 && role_id != 2) {
            resp.sendRedirect(req.getContextPath() + "/error");
        }

        else if(!req.getServletPath().endsWith("user")&&role_id!=1){

            resp.sendRedirect(req.getContextPath() + "/error");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
