package controller;

import dynamic.ProfileAvatar;
import model.Roles;
import model.Users;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/add","/role/delete","/role/update"})
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        ProfileAvatar profileAvatar = new ProfileAvatar();
        profileAvatar.doGet(req, resp);
        switch (path) {
            case "/role":
                getAllRole(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/delete":
                deleteRole(req,resp);
                break;
            case "/role/update":
                updateRole(req,resp);
                break;
            default:
                break;

        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        switch (path) {
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/update":
                updateRole(req,resp);
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
            roleService.insertRole(name,desc);
        }
        req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt((String) req.getParameter("id"));
        boolean deleted = roleService.deleteRole(id);
        System.out.println(deleted + "id "+ id);
    }
    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        if (method.toLowerCase().equals("get")) {
            int id = Integer.parseInt(req.getParameter("id"));
            //System.out.println(id);
            Roles role = roleService.findById(id);
            System.out.println(role);
            req.setAttribute("id",id);
            req.setAttribute("name",role.getName());
            req.setAttribute("desc",role.getDesc());
        }
        if (method.toLowerCase().equals("post")) {
            //int id = (int) req.getAttribute("id");
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            String name = req.getParameter("name");
            String desc = req.getParameter("desc");

            System.out.println(name + desc);
            roleService.updateRole(name,desc, id);
        }
        req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
    }
}
