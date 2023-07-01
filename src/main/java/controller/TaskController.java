package controller;

import dynamic.ProfileAvatar;
import model.Jobs;
import model.Status;
import model.Tasks;
import model.Users;
import service.TaskService;


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
@WebServlet(name = "taskController", urlPatterns = {"/task", "/task/add", "/task/update", "/task/delete", "/task/details"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    ProfileAvatar profileAvatar = new ProfileAvatar();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/task":
                getAllTask(req, resp);
                break;
            case "/task/add":
                addTask(req, resp);
                break;
            case "/task/delete":
                deleteTask(req, resp);
                break;
            case "/task/update":
                updateTask(req, resp);
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
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/task/add":
                addTask(req, resp);
                break;
            case "/task/update":
                updateTask(req, resp);
                break;
            default:
                break;
        }
    }

    private void getAllTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        if (user.getRoleId() == 1) {
            List<Tasks> list = taskService.tasksList();
            req.setAttribute("list", list);
        } else if (user.getRoleId() == 2) {
            List<Tasks> taskManageList = taskService.findTaskListByLeaderId(user.getId());
            req.setAttribute("list", taskManageList);
        }
        List<Status> statusList = taskService.findAllStatus();
        req.setAttribute("statusList", statusList);
        List<Users> userList = taskService.findAllUser();
        req.setAttribute("userList", userList);
        List<Jobs> jobList = taskService.jobsList();
        req.setAttribute("jobList", jobList);
        System.out.println(jobList);
        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }
    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");

        List<Jobs> jobsList = new ArrayList<>();
        if (user.getRoleId() == 1) {
            jobsList  = taskService.jobsList() ;
        } else if (user.getRoleId() == 2) {
            jobsList  =  taskService.findJobListByLeaderId(user.getId());
        }
        req.setAttribute("jobsList", jobsList);

        List<Users> memberList = taskService.findUserListByRoleId(3);
        req.setAttribute("memberList", memberList);

        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
            Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));
            int userId = Integer.parseInt(req.getParameter("memberId"));
            int jobId = Integer.parseInt(req.getParameter("jobId"));
            int statusId = 1; //vì một task mới luôn trong trạng thái chưa thực hiện
            // Integer.parseInt(req.getParameter("statusId"));
            System.out.println(startDate);

            taskService.insertTask(name, startDate, endDate, userId, jobId, statusId);
        }
        req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = taskService.deleteTask(id);
        System.out.println(deleted);
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");

        List<Jobs> jobsList = new ArrayList<>();
        if (user.getRoleId() == 1) {
            jobsList  = taskService.jobsList() ;
        } else if (user.getRoleId() == 2) {
            jobsList  =  taskService.findJobListByLeaderId(user.getId());
        }
        req.setAttribute("jobsList", jobsList);

        List<Users> memberList = taskService.findUserListByRoleId(3);
        req.setAttribute("memberList",memberList);

        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);

        Tasks task = taskService.findTaskById(id);
        req.setAttribute("name", task.getName());
        req.setAttribute("startDate", task.getStartDate());
        req.setAttribute("endDate", task.getEndDate());

        Users member = taskService.findUserById(task.getUserId());
        req.setAttribute("userId", member.getId());
        req.setAttribute("userName", member.getFullname());

        Jobs job = taskService.findJobById(task.getJobId());
        req.setAttribute("jobId", job.getId());
        req.setAttribute("jobName", job.getName());

        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
            Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));
            int userId = Integer.parseInt(req.getParameter("user"));
            int jobId = Integer.parseInt(req.getParameter("job"));

            taskService.updateTask(name, startDate, endDate, userId, jobId, id);
            System.out.println(taskService.updateTask(name, startDate, endDate, userId, jobId, id));
        }
        req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
    }
}
