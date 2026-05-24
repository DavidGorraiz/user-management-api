package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<RoleDTO> getAll();
    Optional<RoleDTO> getById(long id);
    RoleDTO updateRole(long id, RoleUpdateDTO role);
}
