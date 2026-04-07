package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
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
}
