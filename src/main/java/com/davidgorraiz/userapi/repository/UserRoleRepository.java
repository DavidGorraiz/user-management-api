package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleRepository {
    List<UserRoleDTO> getAll();
}
