package service;

import model.Users;
import repository.UserRepository;

import java.util.List;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    public List<Users> checkLogin(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }
    public Users findUserByEmailAndPassword(String email, String password) {
        List<Users> list = userRepository.findByEmailAndPassword(email, password);
        return list.get(0);
    }
}
