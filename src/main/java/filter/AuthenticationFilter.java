package filter;

import model.Jobs;
import model.Tasks;
import model.Users;
import service.JobService;
import service.TaskService;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "authenticationFilter",urlPatterns = {"/role","/job", "/task"})
public class AuthenticationFilter implements Filter {
    JobService jobService = new JobService();
    TaskService taskService = new TaskService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter authentication đã được kích hoạt");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        int role_id = user.getRole_id();
        System.out.println(role_id + "day la role_id cua authentication");
        System.out.println(!req.getServletPath().endsWith("task") + " endwith");
        if (role_id != 1 && role_id != 2) {
            // nếu người dùng không có quyền, thông báo lỗi
            resp.sendRedirect(req.getContextPath() + "/error");
            //return;

        }
        else if(role_id == 2 &&req.getServletPath().startsWith("/role")){
            resp.sendRedirect(req.getContextPath() + "/error");
        }
        else if (role_id == 2 && req.getServletPath().startsWith("/job")&&!req.getServletPath().endsWith("/job")) {

//            if (req.getServletPath().endsWith("/job")) {
//                resp.sendRedirect(req.getContextPath() + "/jobForManager");
//            } else {
            //if(req.getServletPath().startsWith("/job")&&!req.getServletPath().endsWith("/job")) {
                int job_id = Integer.parseInt(req.getParameter("id"));

                System.out.println(job_id + " day la job id");
                Jobs job = jobService.findById(job_id);
                int leader_id = job.getLeader_id();
                System.out.println("leader_id :" + leader_id);
                System.out.println("user_id" + user.getId());
                if (leader_id != user.getId()) {
                    resp.sendRedirect(req.getContextPath() + "/error");
                }
           // }

        }else if(role_id == 2 && req.getServletPath().startsWith("/task")&& !req.getServletPath().endsWith("task")){
//            if (req.getServletPath().endsWith("/task")) {
//                resp.sendRedirect(req.getContextPath() + "/taskForManager");
//            }
        //    else{
            //if(req.getServletPath().endsWith("task")) {
                int task_id = Integer.parseInt(req.getParameter("id"));
                System.out.println(task_id + " day la task id");
                Tasks task = taskService.findById(task_id);
                int task_userId = task.getUser_id();
                System.out.println("leader_id :" + task_userId);
                System.out.println("user_id" + user.getId());
                if (task_userId != user.getId()) {
                    resp.sendRedirect(req.getContextPath() + "/error");
                }
           // }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
    }
}
