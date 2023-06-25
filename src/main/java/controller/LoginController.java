package controller;

import model.LoginDto;
import model.Users;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sessionRemember = req.getSession();
        LoginDto saveMe = (LoginDto) sessionRemember.getAttribute("saveMe");
        String email = "";
        String password = "";
        if (saveMe != null) {
            email = saveMe.getEmail();
            password = saveMe.getPassword();
        }
        req.setAttribute("username", email);
        req.setAttribute("password", password);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //Bước 1:  lấy tham số

        String remember = req.getParameter("remember");
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        LoginDto saveMe;
        LoginDto loginDto;
        List<Users> list = loginService.checkLogin(email, password);
        Boolean isSuccess = list.size() > 0;
        String contextPath = req.getContextPath();
        if (isSuccess) {
            HttpSession sessionRemember = req.getSession();
            if (remember != null) {
                System.out.println("Đã nhảy vào 'remember!=null'");

                HttpSession session = req.getSession();
                saveMe = new LoginDto(email, password);
                session.setAttribute("saveMe", saveMe);

            } else {
                sessionRemember.invalidate();
            }
            Users user = loginService.findUserByEmailAndPassword(email, password);
            System.out.println(user + " day la user dc tim thay");

            HttpSession sessionUser = req.getSession();
            sessionUser.setAttribute("user", user);

            HttpSession sessionCheckLogin = req.getSession();
            loginDto = new LoginDto(email, password);
            sessionCheckLogin.setAttribute("loginDto", loginDto);
            resp.sendRedirect(contextPath);

        } else {
            System.out.println("Đã nhảy vào 'remember==null'");
            resp.sendRedirect(contextPath + "/login");
        }
    }
}
