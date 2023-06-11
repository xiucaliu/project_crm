package service;

import model.Users;
import repository.UserRepository;

import java.util.List;

public class LoginService {
    //có thể nhận thêm tham số nhưng phải có các tham số và repository cần
    private UserRepository userRepository = new UserRepository();
    public List<Users> checkLogin(String email, String password){
        List<Users> list= userRepository.findByEmailAndPassword(email, password);
        return list;
    }
}
