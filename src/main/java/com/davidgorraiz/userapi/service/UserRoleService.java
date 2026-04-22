package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDTO> getAll(){
        return this.userRoleRepository.getAll();
    }
}
