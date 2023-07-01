package service;

import model.Jobs;
import model.Tasks;
import repository.JobRepository;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class FilterService {
    private JobRepository jobRepository = new JobRepository();
    private TaskRepository taskRepository = new TaskRepository();
    public Jobs findJobById(int id) {
        return jobRepository.findById(id);
    }
//    //Hàm checkDirectReport dùng để kiểm tra xem một nhân viên có dưới sự quản lý của một quản lý không
//    public Boolean checkDirectReport(int leaderId, int userId) {
//        List<Jobs> jobList = jobRepository.findByLeaderId(leaderId);
//        List<Tasks> tasksList = new ArrayList<>();
//        List<Integer> userIdList = new ArrayList<>();
//        for (Jobs job : jobList) {
//            List<Tasks> tLisk = taskRepository.findByJobId(job.getId());
//            for (Tasks task : tLisk) {
//                tasksList.add(task);
//            }
//        }
//        for (Tasks task : tasksList) {
//            userIdList.add(task.getUserId());
//        }
//        for (int uId : userIdList) {
//            if (uId == userId) {
//                return true;
//            }
//        }
//        return false;
//    }
    /*    Hàm checkTaskIdMatchLeaderId dùng để kiểm tra xem một task
     có dưới sự quản lý của một leader dùng tham số taskId và leaderId
     */
    public Boolean checkTaskIdMatchLeaderId(int taskId, int leaderId) {
        Tasks task = taskRepository.findById(taskId);
        int jobId = task.getJobId();
        Jobs job = jobRepository.findById(jobId);
        int jobLeaderId = job.getLeaderId();

        if (jobLeaderId != leaderId) {
            return false;
        }
        return true;
    }

}
