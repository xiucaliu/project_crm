package controller;

import dynamic.ProfileAvatar;
import dynamic.TaskStatistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "dashboardServlet", urlPatterns = {""})
public class DashboardController extends HttpServlet {
    ProfileAvatar profileAvatar = new ProfileAvatar();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        profileAvatar.doGet(req, resp);

        TaskStatistic taskStatistic = new TaskStatistic();
        taskStatistic.doGet(req, resp);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
