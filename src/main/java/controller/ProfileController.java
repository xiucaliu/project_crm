package controller;

import dynamic.ProfileAvatar;
import dynamic.TaskStatistic;
import model.*;
import service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile/update"})
public class ProfileController extends HttpServlet {
    ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/profile":
                TaskStatistic taskStatistic = new TaskStatistic();
                taskStatistic.doGet(req, resp);
                profile(req, resp);
                break;
            case "/profile/update":
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

        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);

        switch (path) {
            case "/profile/update":
                profileEdit(req, resp);
                break;
            default:
                break;
        }
    }

    private void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession sessionCheckLogin = req.getSession();
        LoginDto loginDto = (LoginDto) sessionCheckLogin.getAttribute("loginDto");
        System.out.println(loginDto);
        //if (loginDto != null) { // vì đã có filter nên ko có trường hợp login null
        String email = loginDto.getEmail();
        req.setAttribute("email", email);

        String password = loginDto.getPassword();
        Users user = profileService.findUserByEmailAndPassword(email, password);
        req.setAttribute("name", user.getFullname());
        req.setAttribute("avatar", user.getAvatar());

        List<Tasks> taskList = profileService.findTaskListByUserId(user.getId());
        req.setAttribute("taskList", taskList);

        List<Jobs> jobList = profileService.jobsList();
        req.setAttribute("jobList", jobList);
        List<Status> statusList = profileService.findAllStatus();
        profileService.taskStatusPercent(statusList,taskList);
        req.setAttribute("statusList", statusList);

        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    private void profileEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);

        Tasks task = profileService.findTaskById(id);
        req.setAttribute("taskName", task.getName());

        Date startDate = task.getStartDate();
        req.setAttribute("startDate", startDate);

        Date endDate = task.getEndDate();
        req.setAttribute("endDate", endDate);

        int jobId = task.getJobId();
        Jobs job = profileService.findJobById(jobId);
        req.setAttribute("jobName", job.getName());

        List<Status> statusList = profileService.findAllStatus();
        req.setAttribute("statusList", statusList);

        int statusId = task.getStatusId();
        req.setAttribute("statusId", statusId);

        Status status = profileService.findStatusById(statusId);
        req.setAttribute("statusName", status.getName());

        String method = req.getMethod();
        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            statusId = Integer.parseInt(req.getParameter("statusId"));
            boolean update = profileService.updateTaskStatus(statusId, id);
        }
        req.getRequestDispatcher("/profile-update.jsp").forward(req, resp);
    }


}
