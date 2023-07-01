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
import java.util.List;

public class ProfileService {
    private JobRepository jobRepository = new JobRepository();
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();
    private StatusRepository statusRepository = new StatusRepository();
    public List<Status> findAllStatus() {
        return statusRepository.findAllStatus();
    }
    public Status findStatusById(int id) {
        return statusRepository.findById(id);
    }
    public List<Jobs> jobsList() {
        return jobRepository.findAllJob();
    }
    public Tasks findTaskById(int id){
        return  taskRepository.findById(id);
    }
    public Jobs findJobById(int id) {
        return jobRepository.findById(id);
    }
    public Users findUserByEmailAndPassword(String email, String password) {
        List<Users> list = userRepository.findByEmailAndPassword(email, password);
        return list.get(0);
    }
    public boolean updateTaskStatus(int statusId, int id){
        return taskRepository.updateTaskStatus(statusId, id);
    }
    public List<Tasks> findTaskListByUserId(int userId) {
        return taskRepository.findByUserId(userId);
    }
    public List<Tasks> findTaskListByLeaderId(int leaderId) {
        List<Jobs> jobList = jobRepository.findByLeaderId(leaderId);
        List<Tasks> tasksList = new ArrayList<>();
        for (Jobs job : jobList) {
            List<Tasks> tList = taskRepository.findByJobId(job.getId());
//            for (Tasks task : tList) {
//                tasksList.add(task);
//            }
            tasksList.addAll(tList);
        }

        return tasksList;
    }
    public void countTaskStatus(List<Status> statusList, List<Tasks> taskList) {
        for(Status status: statusList){
            int countTask = 0;
            for (Tasks item : taskList) {
                if (item.getStatusId() == status.getId()) {
                    countTask++;
                    status.setCountTaskStatus(countTask);
                }
            }
            System.out.println(countTask + " countTask");
        }
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
