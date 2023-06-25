package service;

import model.Jobs;
import model.Tasks;

public class FilterService {
    private JobService jobService = new JobService();
    private TaskService taskService = new TaskService();
    public Jobs findJobById(int id) {
        return jobService.findJobById(id);
    }
    /*    Hàm checkTaskIdMatchLeaderId dùng để kiểm tra xem một task
     có dưới sự quản lý của một leader dùng tham số taskId và leaderId
     */
    public Boolean checkTaskIdMatchLeaderId(int taskId, int leaderId) {
        Tasks task = taskService.findTaskById(taskId);
        int jobId = task.getJobId();
        Jobs job = jobService.findJobById(jobId);
        int jobLeaderId = job.getLeaderId();

        if (jobLeaderId != leaderId) {
            return false;
        }
        return true;
    }

}
