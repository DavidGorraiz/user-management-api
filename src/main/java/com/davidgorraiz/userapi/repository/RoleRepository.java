package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.RoleDTO;

import java.util.List;

public interface RoleRepository {
    List<RoleDTO> getAll();
}
