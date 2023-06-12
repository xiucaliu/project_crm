package controller;

import model.*;
import service.RoleService;
import service.StatusService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import dynamic.ProfileAvatar;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user/add", "/user/delete", "/user/update","/user/details"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    private TaskService taskService = new TaskService();
    private StatusService statusService = new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);

        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);

        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deletedUser(req, resp);
                break;
            case "/user/update":
                updateUser(req, resp);
                break;
            case "/user/details":
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
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/update":
                updateUser(req, resp);
                break;
            default:
                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> list = userService.findAllUser();
        req.setAttribute("list", list);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        List<Roles> roleList = roleService.rolesList();
        req.setAttribute("roleList", roleList);
        if (method.toLowerCase().equals("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = Integer.parseInt(req.getParameter("role_id"));
            String avatar = req.getParameter("avatar");
            userService.insertUser(email, password, fullname, avatar,roleId);

        }
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void deletedUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt((String) req.getParameter("id"));
        boolean deleted = userService.deleteUser(id);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.toLowerCase().equals("get")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            Users user = userService.findById(id);
            String email = user.getEmail();
            System.out.println(email);
            int role_id = user.getRole_id();
            Roles role = roleService.findById(role_id);
            List<Roles> roleList = roleService.rolesList();
            req.setAttribute("id", id);
            req.setAttribute("fullname", user.getFullname());
            req.setAttribute("email",  user.getEmail());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("roleList", roleList);
            req.setAttribute("role_name",  role.getName());
            req.setAttribute("role_id", role_id);
            req.setAttribute("avatar",user.getAvatar());

        }

        if (method.toLowerCase().equals("post")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("check again "+ id);
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String avatar = req.getParameter("avatar");/*
            Part filePart = req.getPart("avatar");
            String fileName = filePart.getSubmittedFileName();
            String fileDirectory = System.getProperty("java.io.tmpdir");
            System.out.println( fileName + "   "+ filePart );
            System.out.println(fileDirectory);*/
            int role_id = Integer.parseInt(req.getParameter("role_id"));
            System.out.println("role_id: "+role_id);
            boolean update = userService.updateUser(fullname, email, password, avatar,role_id, id);

        }
        req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
    }
    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Users user = userService.findById(id);

        List<Tasks> tasksList = taskService.findByUserId(id);

        List<Status> statusList = statusService.findAllStatus();
        String email = user.getEmail();
        String name = user.getFullname();
        req.setAttribute("name",name);
        req.setAttribute("email",email);
        req.setAttribute("tasksList",tasksList);
        req.setAttribute("statusList",statusList);
        req.setAttribute("avatar",user.getAvatar());
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}
