package controller;

import dynamic.ProfileAvatar;
import model.*;
import service.JobService;
import service.StatusService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile/edit"})
public class ProfileController extends HttpServlet {
    UserService userService = new UserService();
    TaskService taskService = new TaskService();
    StatusService statusService = new StatusService();
    JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/profile":
                profile(req, resp);
                break;
            case "/profile/edit":
                profileEdit(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/profile/edit":
                profileEdit(req, resp);
                break;
            default:
                break;
        }
    }

    private void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionCheckLogin = req.getSession();
        LoginDto loginDto = (LoginDto) sessionCheckLogin.getAttribute("loginDto");
        System.out.println(loginDto);
        if (loginDto != null) { // vì đã có filter nên ko có trường hợp loginsetA null
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        System.out.println(email);
        Users user = userService.findByEmailAndPassword(email, password);
        req.setAttribute("name", user.getFullname());
        req.setAttribute("email", email);
        req.setAttribute("avatar", user.getAvatar());
        System.out.println(user.getAvatar());
        List<Tasks> taskList = taskService.findByUserId(user.getId());
        req.setAttribute("taskList", taskList);

        List<Jobs> jobList = jobService.jobsList();
        req.setAttribute("jobList", jobList);

        List<Status> statusList = statusService.findAllStatus();
        req.setAttribute("statusList", statusList);
         }
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    private void profileEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        Tasks task = taskService.findById(id);
        req.setAttribute("task_name", task.getName());
        Date start_date = task.getStart_date();
        req.setAttribute("start_date", start_date);
        Date end_date = task.getEnd_date();
        req.setAttribute("end_date", end_date);
        int job_id = task.getJob_id();
        Jobs job = jobService.findById(job_id);
        //req.setAttribute("job_id",job_id);
        req.setAttribute("job_name", job.getName());
        List<Status> statusList = statusService.findAllStatus();
        req.setAttribute("statusList", statusList);
        int status_id = task.getStatus_id();
        req.setAttribute("status_id", status_id);
        Status status = statusService.findById(status_id);
        req.setAttribute("status_name", status.getName());
        String method = req.getMethod();
        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            status_id = Integer.parseInt(req.getParameter("status_id"));
            boolean update = taskService.updateTaskStatus(status_id, id);
            if (update) {
                System.out.println("update status successful");
            }

        }
        req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
    }


}
