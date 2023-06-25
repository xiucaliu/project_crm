package service;

import model.Roles;
import model.Status;
import model.Tasks;
import model.Users;
import repository.RoleRepository;
import repository.StatusRepository;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();

    private StatusRepository statusRepository = new StatusRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private ProfileService profileService = new ProfileService();

    public List<Status> findAllStatus() {
        return statusRepository.findAllStatus();
    }

    public List<Roles> rolesList() {
        return roleRepository.findAllRole();
    }

    public Roles findRoleById(int id) {
        return roleRepository.findById(id);
    }

    public List<Tasks> findTaskByUserId(int userId) {
        return taskRepository.findByUserId(userId);
    }

    public boolean insertUser(String email, String password, String fullname, String avatar, int roleId) {
        return userRepository.insertUser(email, password, fullname, avatar, roleId);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public boolean updateUser(String fullname, String email, String password, String avatar, int roleId, int id) {
        return userRepository.updateUser(fullname, email, password, avatar, roleId, id);
    }
    public List<Users> findAllUser() {
        return userRepository.findAllUser();
    }
    public Users findUserById(int id) {
        return userRepository.findById(id);
    }
    public List<Users> findUserListByRoleId(int roleId){
        return userRepository.findByRoleId(roleId);
    }
    public void taskStatusPercent(List<Status> statusList, List<Tasks> taskList) {
        profileService.taskStatusPercent(statusList, taskList);
    }
}
