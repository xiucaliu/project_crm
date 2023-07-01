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

public class JobService {
    private JobRepository jobRepository = new JobRepository();
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public List<Status> findAllStatus() {
        return statusRepository.findAllStatus();
    }

    public Users findUserById(int id) {
        return userRepository.findById(id);
    }

    public List<Users> findUserListByRoleId(int roleId) {
        return userRepository.findByRoleId(roleId);
    }

    public List<Jobs> jobsList() {
        return jobRepository.findAllJob();
    }

    public boolean insertJob(String name, int leaderId, Date startDate, Date endDate) {
        return jobRepository.insertJob(name, leaderId, startDate, endDate);
    }

    public boolean deleteJob(int id) {
        return jobRepository.deleteJob(id);
    }

    public Jobs findJobById(int id) {
        return jobRepository.findById(id);
    }

    public boolean updateJob(String name, Date startDate, Date endDate, int leaderId, int id) {
        return jobRepository.updateJob(name, startDate, endDate, leaderId, id);
    }

    public List<Jobs> findJobListByLeaderId(int leaderId) {
        return jobRepository.findByLeaderId(leaderId);
    }

    public List<Tasks> findTaskListByJobId(int jobId) {
        return taskRepository.findByJobId(jobId);
    }

    public List<Users> findUserListByTaskList(List<Tasks> taskList) {
        ArrayList<Integer> userIdList = new ArrayList<>();
        List<Users> userList = new ArrayList<>();
        for (Tasks task : taskList) {
            boolean existed = false;
            for (Integer uId : userIdList) {
                if (task.getUserId() == uId) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                userIdList.add(task.getUserId());
                System.out.println(task.getUserId());
                Users user = userRepository.findById(task.getUserId());
                System.out.println(user);
                userList.add(user);
            }
        }
        return userList;
    }

    public void taskStatusPercent(List<Status> statusList, List<Tasks> taskList) {
        int allTask = taskList.size();
        for (Status status : statusList) {
            int countTask = 0;
            for (Tasks item : taskList) {
                if (item.getStatusId() == status.getId()) {
                    countTask++;
                }
            }
            System.out.println(countTask + " countTask");

            if (allTask != 0) {
                float taskStatusPercent = (float) (Math.floor(((float) countTask / allTask * 100) * 10) / 10);
                status.setTaskStatusPercent(taskStatusPercent);
                System.out.println(taskStatusPercent + " taskStatusPercent");
            }
        }
    }
}
