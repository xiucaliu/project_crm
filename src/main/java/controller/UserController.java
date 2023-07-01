package controller;

import model.*;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import dynamic.ProfileAvatar;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user/add", "/user/delete", "/user/update", "/user/details"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    ProfileAvatar profileAvatar = new ProfileAvatar();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);

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
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/user/add":
                addUser(req, resp);
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
        List<Roles> roleList = userService.rolesList();
        req.setAttribute("roleList", roleList);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        List<Roles> roleList = userService.rolesList();
        req.setAttribute("roleList", roleList);
        if (method.toLowerCase().equals("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = Integer.parseInt(req.getParameter("role"));
            String avatar = req.getParameter("avatar");
            boolean insertUser = userService.insertUser(email, password, fullname, avatar, roleId);
            System.out.println(insertUser + " add user success?");

        }
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void deletedUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = userService.deleteUser(id);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        Users user = userService.findUserById(id);
        String email = user.getEmail();
        System.out.println(email);
        Roles role = userService.findRoleById(user.getRoleId());
        List<Roles> roleList = userService.rolesList();
        req.setAttribute("id", id);
        req.setAttribute("fullname", user.getFullname());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("roleList", roleList);
        req.setAttribute("roleName", role.getName());
        req.setAttribute("roleId", user.getRoleId());
        req.setAttribute("avatar", user.getAvatar());

        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            String fullname = req.getParameter("fullname");
            email = req.getParameter("email");
            String password = req.getParameter("password");
            String avatar = req.getParameter("avatar");
            int roleId = Integer.parseInt(req.getParameter("role"));
            boolean update = userService.updateUser(fullname, email, password, avatar, roleId, id);
            System.out.println(update + " update success? ");
        }
        req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Users user = userService.findUserById(id);
        List<Tasks> tasksList = userService.findTaskByUserId(id);
        List<Status> statusList = userService.findAllStatus();
        String email = user.getEmail();
        String name = user.getFullname();
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("tasksList", tasksList);
        req.setAttribute("statusList", statusList);
        req.setAttribute("avatar", user.getAvatar());
        userService.taskStatusPercent(statusList,tasksList);
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}
