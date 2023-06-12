package dynamic;

import model.LoginDto;
import model.Users;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebServlet(name="profileAvatar")
public class ProfileAvatar extends HttpServlet {

    UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionCheckLogin = req.getSession();
        LoginDto loginDto = (LoginDto) sessionCheckLogin.getAttribute("loginDto");
        if (loginDto != null) { // vì đã có filter nên ko có trường hợp loginsetA null
            String email = loginDto.getEmail();
            String password = loginDto.getPassword();
            System.out.println(email);
            Users user = userService.findByEmailAndPassword(email, password);
            req.setAttribute("profile_avatar", user.getAvatar());
        }

    }
}
