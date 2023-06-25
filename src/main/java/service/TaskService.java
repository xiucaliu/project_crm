package service;

import model.Jobs;
import model.Status;
import model.Tasks;
import model.Users;
import repository.JobRepository;
import repository.StatusRepository;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {
    private JobService jobService = new JobService();
    private UserService userService = new UserService();
    private TaskRepository taskRepository = new TaskRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public List<Status> findAllStatus() {
        return statusRepository.findAllStatus();
    }
    public List<Tasks> tasksList() {
        return taskRepository.findAllTask();
    }
    public boolean insertTask(String name, Date startDate, Date endDate, int userId, int jobId, int statusId) {
        return taskRepository.insertTask(name, startDate, endDate, userId, jobId, statusId);
    }

    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
    public boolean updateTask(String name, Date startDate, Date endDate, int userId, int jobId, int id) {
        return taskRepository.updateTask(name, startDate, endDate, userId, jobId, id);
    }
    public List<Jobs> jobsList() {
        return jobService.jobsList();
    }
    public Jobs findJobById(int id) {
        return jobService.findJobById(id);
    }

    public Tasks findTaskById(int id) {
        return taskRepository.findById(id);
    }

    public List<Jobs> findJobListByLeaderId(int leaderId) {
        return jobService.findJobListByLeaderId(leaderId);
    }

    public Users findUserById(int id) {
        return userService.findUserById(id);
    }

    public List<Users> findUserListByRoleId(int roleId) {
        return userService.findUserListByRoleId(roleId);
    }

    public List<Users> findAllUser() {
         return userService.findAllUser();
    }

    public List<Tasks> findTaskListByLeaderId(int leaderId) {
        List<Jobs> jobList = jobService.findJobListByLeaderId(leaderId);
        List<Tasks> tasksList = new ArrayList<>();
        for (Jobs job : jobList) {
            List<Tasks> tList = taskRepository.findByJobId(job.getId());
            tasksList.addAll(tList);
        }
        return tasksList;
    }
}
