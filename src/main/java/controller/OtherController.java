package controller;

import dynamic.ProfileAvatar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "otherController", urlPatterns = {"/blank","/error"})
public class OtherController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/blank":
                ProfileAvatar profileAvatar = new ProfileAvatar();
                profileAvatar.doGet(req, resp);
                blank(req, resp);
                break;
            case "/error":
                error(req, resp);
                break;
            default:
                break;

        }
    }
    private void blank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/blank.jsp").forward(req,resp);
    }
    private void error(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/404.jsp").forward(req,resp);
    }
}
