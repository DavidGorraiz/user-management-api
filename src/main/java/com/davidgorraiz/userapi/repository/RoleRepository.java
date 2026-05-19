package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<RoleDTO> getAll();
    Optional<RoleDTO> getById(long id);
}
