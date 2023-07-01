package controller;

import dynamic.ProfileAvatar;
import model.Roles;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/add", "/role/delete", "/role/update"})
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();
    ProfileAvatar profileAvatar = new ProfileAvatar();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/role":
                getAllRole(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/delete":
                deleteRole(req, resp);
                break;
            case "/role/update":
                updateRole(req, resp);
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
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/update":
                updateRole(req, resp);
                break;
            default:
                break;
        }
    }
    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Roles> list = roleService.rolesList();
        req.setAttribute("list", list);
        req.getRequestDispatcher("role.jsp").forward(req, resp);
    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.toLowerCase().equals("post")) {
            String name = req.getParameter("name");
            String desc = req.getParameter("desc");
            roleService.insertRole(name, desc);
        }
        req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = roleService.deleteRole(id);
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        int id = Integer.parseInt(req.getParameter("id"));
        Roles role = roleService.findRoleById(id);
        System.out.println(role);
        req.setAttribute("id", id);
        req.setAttribute("name", role.getName());
        req.setAttribute("desc", role.getDesc());

        if (method.toLowerCase().equals("post")) {
            id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String desc = req.getParameter("desc");
            roleService.updateRole(name, desc, id);
        }
        req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
    }
}
