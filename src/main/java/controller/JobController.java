package controller;

import dynamic.ProfileAvatar;
import model.*;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "jobController", urlPatterns = {"/job", "/job/add", "/job/delete", "/job/update", "/job/details"})
public class JobController extends HttpServlet {
    JobService jobService = new JobService();
    ProfileAvatar profileAvatar = new ProfileAvatar();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/job":
                getAllJob(req, resp);
                break;
            case "/job/add":
                addJob(req, resp);
                break;
            case "/job/delete":
                deleteJob(req, resp);
                break;
            case "/job/update":
                updateJob(req, resp);
                break;
            case "/job/details":
                details(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/job/add":
                addJob(req, resp);
                break;
            case "/job/update":
                updateJob(req, resp);
                break;
            default:
                break;
        }
    }
    private void getAllJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");

        if (user.getRoleId() == 1) {
            List<Users> leaderList = jobService.findUserListByRoleId(2);
            req.setAttribute("leaderList", leaderList);
            System.out.println("leader list: "+leaderList);

            List<Jobs> list = jobService.jobsList();
            req.setAttribute("list", list);
        } else if (user.getRoleId() == 2) {
            List<Users> leaderList = new ArrayList<>();
            int leaderId = user.getId();
            Users leader = jobService.findUserById(leaderId);
            leaderList.add(leader);
            req.setAttribute("leaderList", leaderList);

            List<Jobs> list = jobService.findJobListByLeaderId(user.getId());
            req.setAttribute("list", list);
        }
        req.getRequestDispatcher("/job.jsp").forward(req, resp);
    }

    private void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        List<Users> leaderList = jobService.findUserListByRoleId(2);
        req.setAttribute("leaderList", leaderList);
        System.out.println("check1");

        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            System.out.println("check2");
            int leaderId = Integer.parseInt(req.getParameter("leaderId"));
            Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
            System.out.println("check3");
            Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));
            System.out.println(startDate);
            System.out.println("check4");
            jobService.insertJob(name,leaderId, startDate, endDate);
        }
        req.getRequestDispatcher("/job-add.jsp").forward(req, resp);
    }

    private void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id + " id");
        boolean deleted = jobService.deleteJob(id);
        System.out.println(deleted);
    }

    private void updateJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        String method = req.getMethod();

        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);

        Jobs job = jobService.findJobById(id);
        req.setAttribute("name", job.getName());
        req.setAttribute("startDate", job.getStartDate());
        req.setAttribute("endDate", job.getEndDate());
        req.setAttribute("leaderId",job.getLeaderId());
        Users leader = jobService.findUserById(job.getLeaderId());
        req.setAttribute("leaderName",leader.getFullname());
        List<Users> leaderList = new ArrayList<>();
        if (user.getRoleId() == 1) {
            leaderList = jobService.findUserListByRoleId(2);
            System.out.println("leader list: "+leaderList);

        } else {
            leaderList.add(user);
        }
        req.setAttribute("leaderList", leaderList);
        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
            Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));
            int leaderId = Integer.parseInt(req.getParameter("leader"));
            jobService.updateJob(name, startDate, endDate, leaderId, id);
        }
        req.getRequestDispatcher("/job-update.jsp").forward(req, resp);
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Tasks> taskList = jobService.findTaskListByJobId(id);
        req.setAttribute("taskList", taskList);

        List<Users> userList = jobService.findUserListByTaskList(taskList);
        req.setAttribute("userList", userList);

        List<Status> statusList = jobService.findAllStatus();
        req.setAttribute("statusList", statusList);

        jobService.taskStatusPercent(statusList, taskList);

        req.getRequestDispatcher("/job-details.jsp").forward(req, resp);
    }
}
