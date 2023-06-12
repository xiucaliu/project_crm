package service;

import model.Jobs;
import repository.JobRepository;

import java.util.Date;
import java.util.List;

public class JobService {
    JobRepository jobRepository = new JobRepository();

    public List<Jobs> jobsList() {
        return jobRepository.findAllJob();
    }

    public boolean insertJob(String name, int leader_id, Date start_date, Date end_date) {
        return jobRepository.insertJob(name, leader_id, start_date, end_date);
    }

    public boolean deleteJob(int id) {
        return jobRepository.deleteJob(id);
    }

    public Jobs findById(int id) {
        return jobRepository.findById(id);
    }

    public boolean updateJob(String name, Date start_date, Date end_date, int leader_id, int id) {
        return jobRepository.updateJob(name, start_date, end_date, leader_id, id);
    }

    public List<Jobs> findByLeaderId(int leader_id) {
        return jobRepository.findByLeaderId(leader_id);
    }
}
