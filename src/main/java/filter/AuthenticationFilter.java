package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "authenticationFilter",urlPatterns = {"/role","/job","/user"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter authentication đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sessionCheckRole = req.getSession();
        System.out.println(req.getContextPath() + "day la contextPath");
        /*String role_id = (String) sessionCheckRole.getAttribute("role_id");
        System.out.println(role_id + " day la role_id cua authentication");
            if(role_id.equals("1")){
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else{
                resp.sendRedirect(req.getContextPath() + "/error");
            }*/
       /* if (sessionCheckRole == null || sessionCheckRole.getAttribute("role_id") == null) {
            // nếu người dùng chưa được xác thực, chuyển hướng đến trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }*/
        //vì đã login nên chắc chắn có role_id;
        String role_id = (String) sessionCheckRole.getAttribute("role_id");
        System.out.println(role_id + "day la role_id cua authentication");
        if (!role_id.equals("1")) {
            // nếu người dùng không có quyền, thông báo lỗi
            resp.sendRedirect(req.getContextPath() + "/error");
            //return;
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {
    }
}
