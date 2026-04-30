package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> getAll();
    UserDTO getById(long id);
}
