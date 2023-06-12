//package filter;
//
//import model.Users;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class UserEditFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        HttpSession sessionUser = req.getSession();
//        Users user = (Users) sessionUser.getAttribute("user");
//        int role_id = user.getRole_id();
//        if (role_id != 1) {
//            resp.sendRedirect(req.getContextPath() + "/error");
//        }
//        else {
//
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
