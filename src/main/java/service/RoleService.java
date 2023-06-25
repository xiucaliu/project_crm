package service;

import model.Roles;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    public List<Roles> rolesList(){
        return roleRepository.findAllRole();
    }
    public boolean insertRole(String name, String desc){
        return roleRepository.insertRole(name,desc);
    }
    public boolean deleteRole(int id) {
        return roleRepository.deleteRole(id);
    }
    public Roles findRoleById(int id){
        return  roleRepository.findById(id);
    }
    public boolean updateRole(String name, String desc, int id){
        return roleRepository.updateRole(name,desc,id);
    }
}
