package controller;

import model.LoginDto;
import model.Users;
import service.LoginService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();
    private UserService userService = new UserService();
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
        LoginDto saveMe = new LoginDto(email,password);
        LoginDto loginDto;
        List<Users> list = loginService.checkLogin(email, password);
        Boolean isSuccess = list.size() > 0;
        String contextPath = req.getContextPath();
        if (isSuccess) {
            HttpSession sessionRemember = req.getSession();
            if (remember != null) {
            System.out.println("Đã nhảy vào 'remember!=null'");

            HttpSession session4 = req.getSession();
            saveMe = new LoginDto(email, password);
            session4.setAttribute("saveMe", saveMe);

            }else {
                sessionRemember.invalidate();
            }
            Users user = userService.findByEmailAndPassword(email,password);
            System.out.println(user + " day la user dc tim thay");
            System.out.println(user.getRole_id() + "day la role_id");

            HttpSession sessionCheckRole = req.getSession();
            String role_id = String.valueOf(user.getRole_id());
            System.out.println(user.getRole_id() + "day la role_id bang chu");
            sessionCheckRole.setAttribute("role_id", role_id);

            HttpSession sessionCheckLogin = req.getSession();
            loginDto = new LoginDto(email, password);
            sessionCheckLogin.setAttribute("loginDto", loginDto);
            resp.sendRedirect(contextPath + "/");

        } else {
            System.out.println("Đã nhảy vào 'remember==null'");
            resp.sendRedirect(contextPath + "/login");
        }
    }
}
