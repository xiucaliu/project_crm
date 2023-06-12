package service;

import model.Users;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    public boolean checkLogin() {
        List<Users> list = userRepository.findAllUser();
        return list.size() > 0;
    }

    public Users findByEmailAndPassword(String email, String password) {
        List<Users> list = userRepository.findByEmailAndPassword(email, password);
        return list.get(0);
    }

    public List<Users> findAllUser() {
        List<Users> list = userRepository.findAllUser();
        return list;
    }

    public boolean insertUser(String email, String password, String fullname,String avatar,int role_id) {
        boolean inserted = userRepository.insertUser(email, password, fullname, avatar,role_id);
        return inserted;
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public boolean updateUser(String fullname, String email, String password, String avatar,int role_id, int id) {
        return userRepository.updateUser(fullname, email, password, avatar, role_id, id);
    }
    public Users findById(int id){
        return  userRepository.findById(id);
    }

    public List<Users> findByRoleId(int role_id) {
        return userRepository.findByRoleId(role_id);
    }
}
