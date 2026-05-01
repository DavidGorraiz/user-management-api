package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDTO> getAll();
    Optional<UserDTO> getById(long id);
}
