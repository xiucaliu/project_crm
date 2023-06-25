package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        HttpSession sessionUser = req.getSession();
        sessionUser.invalidate();
        HttpSession sessionCheckLogin = req.getSession();
        sessionCheckLogin.invalidate();
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
