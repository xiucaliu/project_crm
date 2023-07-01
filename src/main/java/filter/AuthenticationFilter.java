package filter;

import model.Jobs;
import model.Users;
import service.FilterService;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "authenticationFilter",urlPatterns = {"/job/*", "/task"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterService filterService = new FilterService();
        System.out.println("Filter authentication đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");

        int role_id = user.getRoleId();
        if (role_id != 1 && role_id != 2) {
            // nếu người dùng không có quyền, thông báo lỗi
            resp.sendRedirect(req.getContextPath() + "/error");
        } else if (role_id == 2 && req.getServletPath().startsWith("/job") && !req.getServletPath().endsWith("/job")) {
            int job_id = Integer.parseInt(req.getParameter("id"));
            Jobs job = filterService.findJobById(job_id);
            int leader_id = job.getLeaderId();
            if (leader_id != user.getId()) {
                resp.sendRedirect(req.getContextPath() + "/error");
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else if (role_id == 2 && req.getServletPath().startsWith("/task") && !req.getServletPath().endsWith("task")) {
            if(req.getServletPath().endsWith("/add")){
                //manager được thêm task
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            //Kiểm tra task có thuộc sự quản lý của manager không
            int task_id = Integer.parseInt(req.getParameter("id"));
            Boolean match = filterService.checkTaskIdMatchLeaderId(task_id,user.getId());
            if (!match) {
                resp.sendRedirect(req.getContextPath() + "/error");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {
    }
}
