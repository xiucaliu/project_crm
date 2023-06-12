package controller;

import dynamic.ProfileAvatar;
import model.Jobs;
import model.Status;
import model.Tasks;
import model.Users;
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

//import static org.graalvm.compiler.options.OptionType.User;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task/add", "/task/update", "/task/delete", "/task/details","/taskForManager"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    JobService jobService = new JobService();
    UserService userService = new UserService();
    StatusService statusService = new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/task":
                HttpSession sessionUser = req.getSession();
                Users user = (Users) sessionUser.getAttribute("user");
                if(user.getRole_id()==1){
                    getAllTask(req, resp);
                }
                if(user.getRole_id()==2){
                    taskForManager(req, resp);
                }
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
//            case "/taskForManager":
//                taskForManager(req, resp);
//                break;
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
        List<Tasks> list = taskService.tasksList();
        req.setAttribute("list", list);
        List<Users> userList = userService.findAllUser();
        req.setAttribute("userList",userList);
        List<Jobs> jobList = jobService.jobsList();
        req.setAttribute("jobList",jobList);
        System.out.println(jobList);
        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }
    private void taskForManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        int user_id = user.getId();
        List<Tasks> taskManageList = taskService.findByUserId(user_id);
        req.setAttribute("list", taskManageList);
        List<Users> userList = userService.findAllUser();
        req.setAttribute("userList",userList);
        List<Jobs> jobList = jobService.jobsList();
        req.setAttribute("jobList",jobList);
        System.out.println(jobList);
        req.getRequestDispatcher("task.jsp").forward(req, resp);
    }
    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        List<Jobs> jobsList = jobService.jobsList();
        req.setAttribute("jobsList", jobsList);

        List<Users> usersList = userService.findAllUser();
        req.setAttribute("usersList", usersList);

        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            Date start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
            Date end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            int job_id = Integer.parseInt(req.getParameter("job_id"));
            int status_id = 1;/* Integer.parseInt(req.getParameter("status_id"));*/
            System.out.println(start_date);

            taskService.insertTask(name, start_date, end_date, user_id, job_id, status_id);
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
        List<Jobs> jobsList = jobService.jobsList();
        List<Users> usersList = userService.findAllUser();
        //if (method.toLowerCase().equals("get")) {
            req.setAttribute("jobsList", jobsList);
            req.setAttribute("usersList", usersList);
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            Tasks task = taskService.findById(id);
            System.out.println("task user_id là "+task.getUser_id());

            Users user = userService.findById(task.getUser_id());
            System.out.println("user là"+user);
            System.out.println("username là"+user.getId()+user.getFullname());
            req.setAttribute("user_id",user.getId());
            req.setAttribute("user_name",user.getFullname());
            Jobs job = jobService.findById(task.getJob_id());

            req.setAttribute("job_id",job.getId());
            req.setAttribute("job_name",job.getName());
            req.setAttribute("id",id);
            req.setAttribute("name", task.getName());
            req.setAttribute("start_date",task.getStart_date());
            req.setAttribute("end_date",task.getEnd_date());


        //}
        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Date start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
            Date end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            System.out.println(user_id);
            int job_id = Integer.parseInt(req.getParameter("job_id"));
            taskService.updateTask(name,start_date,end_date,user_id,job_id,id);
            System.out.println(taskService.updateTask(name,start_date,end_date,user_id,job_id,id));
        }
        req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
    }



}
