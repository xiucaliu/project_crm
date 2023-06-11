package service;

import model.Status;
import model.Tasks;
import repository.StatusRepository;

import java.util.List;

public class StatusService {
    StatusRepository statusRepository = new StatusRepository();
    public List<Status> findAllStatus() {
        List<Status> list = statusRepository.findAllStatus();
        return list;
    }
    public Status findById(int id) {
        return statusRepository.findById(id);
    }
}
