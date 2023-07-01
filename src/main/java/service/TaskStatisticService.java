package service;

import model.Jobs;
import model.Status;
import model.Tasks;
import repository.JobRepository;
import repository.StatusRepository;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskStatisticService {
    private TaskRepository taskRepository = new TaskRepository();
    private JobRepository jobRepository = new JobRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public List<Status> findAllStatus() {
        return statusRepository.findAllStatus();

    }
    public List<Tasks> tasksList() {
        return taskRepository.findAllTask();
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
        int allTask = taskList.size();
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
}
