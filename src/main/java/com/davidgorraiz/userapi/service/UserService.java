package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        return this.userRepository.getAll();
    }
}
