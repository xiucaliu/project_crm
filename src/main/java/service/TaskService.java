package service;

import model.Tasks;
import model.Users;
import repository.TaskRepository;

import java.util.Date;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();
    public List<Tasks> tasksList(){
        return taskRepository.findAllTask();
    }
    public boolean insertTask(String name, Date start_date, Date end_date, int user_id, int job_id, int status_id){
        return taskRepository.insertTask(name,start_date,end_date, user_id,job_id,status_id);
    }
    public boolean deleteTask(int id){
        return taskRepository.deleteTask(id);
    }
    public Tasks findById(int id){
        return  taskRepository.findById(id);
    }
    public List<Tasks> findByUserId(int user_id){
        return taskRepository.findByUserId(user_id);
    }
    public boolean updateTask(String name, Date start_date, Date end_date,int user_id,int job_id, int id) {
        return taskRepository.updateTask(name, start_date, end_date, user_id, job_id, id);
    }
    public boolean updateTaskStatus(int status_id, int id){
        return taskRepository.updateTaskStatus(status_id, id);
    }
}
