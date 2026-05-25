package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;
import com.davidgorraiz.userapi.exceptions.RoleNotFoundException;
import com.davidgorraiz.userapi.exceptions.UserNotFoundException;
import com.davidgorraiz.userapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> getAll(){
        return this.roleRepository.getAll();
    }

    public RoleDTO getById(long id){
        return this.roleRepository.getById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    public RoleDTO createRole(RoleDTO role){
        return this.roleRepository.createRole(role);
    }

    public RoleDTO updateRole(long id, RoleUpdateDTO role){
        RoleDTO roleFound = getById(id);
        return this.roleRepository.updateRole(id, role);
    }
}
