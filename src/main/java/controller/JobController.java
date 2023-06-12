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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "jobController", urlPatterns = {"/job", "/job/add", "/job/delete", "/job/update", "/job/details","/jobForManager"})
//Dự án
public class JobController extends HttpServlet {
    JobService jobService = new JobService();
    TaskService taskService = new TaskService();
    UserService userService = new UserService();
    StatusService statusService = new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/job":
                HttpSession sessionUser = req.getSession();
                Users user = (Users) sessionUser.getAttribute("user");
                if(user.getRole_id()==1){
                    getAllJob(req, resp);
                }
                if(user.getRole_id()==2){
                    jobForManager(req, resp);
                }
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
            case "/jobForManager":
                jobForManager(req, resp);
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
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
        List<Jobs> list = jobService.jobsList();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/job.jsp").forward(req, resp);
    }

    private void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        /*List<Jobs> jobList = jobService.jobsList();
        System.out.println("jobList" + jobList);
        List<Integer> leader_idList = new ArrayList<>();
        List<Users> leaderList = new ArrayList<Users>();
        for (Jobs job : jobList) {
            boolean existed = false;
            for (int leader_id : leader_idList) {
                if (leader_idList == null) {
                    break;
                } else if (job.getLeader_id() == leader_id) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                leader_idList.add(job.getLeader_id());
                System.out.println(job.getLeader_id());
                Users user = userService.findById(job.getLeader_id());
                System.out.println(user);
                leaderList.add(user);
            }
        }
        System.out.println(leader_idList);

        System.out.println(leaderList);*/
        List<Users> leaderList = userService.findByRoleId(2);
        req.setAttribute("leaderList", leaderList);

        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            int leader_id = Integer.parseInt(req.getParameter("leader_id"));
            Date start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
            Date end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
            System.out.println(start_date);
            jobService.insertJob(name, leader_id, start_date, end_date);
        }
        req.getRequestDispatcher("/job-add.jsp").forward(req, resp);
    }


    private void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt((String) req.getParameter("id"));
        System.out.println(id + " id");
        boolean deleted = jobService.deleteJob(id);
        System.out.println(deleted);
    }

    private void updateJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.toLowerCase().equals("get")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Jobs job = jobService.findById(id);
            System.out.println(job);
            req.setAttribute("id", id);
            req.setAttribute("name", job.getName());
            List<Users> leaderList = userService.findByRoleId(2);
            req.setAttribute("leaderList", leaderList);
            req.setAttribute("start_date", job.getStart_date());
            req.setAttribute("end_date", job.getEnd_date());
        }
        if (method.toLowerCase().equals("post")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            String name = req.getParameter("name");
            int leader_id = Integer.parseInt(req.getParameter("leader_id"));
            Date start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
            Date end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
            jobService.updateJob(name, start_date, end_date, leader_id, id);
        }
        req.getRequestDispatcher("/job-update.jsp").forward(req, resp);
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        List<Tasks> allTaskList = taskService.tasksList();
        List<Tasks> taskList = new ArrayList<>();
        for (Tasks item : allTaskList) {
            if (item.getJob_id() == id) {
                taskList.add(item);
            }
        }
        req.setAttribute("taskList", taskList);
        System.out.println(taskList);

        ArrayList<Integer> user_idList = new ArrayList<>();
        List<Users> userList = new ArrayList<>();
        for (Tasks task : taskList) {
            boolean existed = false;
            for (Integer user_id : user_idList) {
                if (user_idList == null) {
                    break;
                } else if (task.getUser_id() == user_id) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                user_idList.add(task.getUser_id());
                System.out.println(task.getUser_id());
                Users user = userService.findById(task.getUser_id());
                System.out.println(user);
                userList.add(user);
            }
        }
        System.out.println(user_idList);
        System.out.println(userList);
        req.setAttribute("userList", userList);
        System.out.println(userList.get(0).getAvatar() + " day la avatar job details");
        List<Status> statusList = statusService.findAllStatus();
        System.out.println(statusList);
        req.setAttribute("statusList", statusList);
        req.getRequestDispatcher("/job-details.jsp").forward(req, resp);
    }

    private void jobForManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        List<Jobs> jobManageList = jobService.findByLeaderId(user.getId());
        req.setAttribute("list", jobManageList);
        req.getRequestDispatcher("/job.jsp").forward(req, resp);
    }
}
