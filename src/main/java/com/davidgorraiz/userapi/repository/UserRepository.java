package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.UserUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDTO> getAll();
    Optional<UserDTO> getById(long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(long id, UserUpdateDTO userDTO);
    UserDTO deleteUser(long id);
}
