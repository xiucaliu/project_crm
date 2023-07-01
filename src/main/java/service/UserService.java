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
    public List<Status> findAllStatus() {
        List<Status> list = statusRepository.findAllStatus();
        return list;
    }
    public List<Roles> rolesList(){
        return roleRepository.findAllRole();
    }
    public Roles findRoleById(int id){
        return  roleRepository.findById(id);
    }
    public List<Tasks> findTaskByUserId(int userId){
        return taskRepository.findByUserId(userId);
    }
    public boolean insertUser(String email, String password, String fullname,String avatar,int roleId) {
        boolean inserted = userRepository.insertUser(email, password, fullname, avatar,roleId);
        return inserted;
    }
    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }
    public boolean updateUser(String fullname, String email, String password, String avatar,int roleId, int id) {
        return userRepository.updateUser(fullname, email, password, avatar, roleId, id);
    }
    public List<Users> findAllUser() {
        List<Users> list = userRepository.findAllUser();
        return list;
    }
    public Users findUserById(int id){
        return  userRepository.findById(id);
    }
    public void taskStatusPercent( List<Status> statusList,  List<Tasks> taskList) {
        int allTask = taskList.size();
        for(Status status: statusList){
            int countTask = 0;
            for (Tasks item : taskList) {
                if (item.getStatusId() == status.getId()) {
                    countTask++;
                }
            }
            System.out.println(countTask + " countTask");

            if (allTask != 0) {
                float taskStatusPercent = (float) (Math.floor(((float) countTask / allTask * 100) * 100) / 100);
                status.setTaskStatusPercent(taskStatusPercent);
                System.out.println(taskStatusPercent + " taskStatusPercent");
            }
        }
    }

}
