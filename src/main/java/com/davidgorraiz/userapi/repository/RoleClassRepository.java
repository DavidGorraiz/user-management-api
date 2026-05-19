package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.mapper.RoleMapper;
import com.davidgorraiz.userapi.entity.Role;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleClassRepository implements RoleRepository{

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    public RoleClassRepository(JpaRoleRepository jpaRoleRepository, RoleMapper roleMapper) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> getAll() {
        return this.roleMapper.toDtos(this.jpaRoleRepository.findAll());
    }

    @Override
    public RoleDTO getById(long id) {
        Role roleEntity = this.jpaRoleRepository.findById(id).orElse(null);
        if (roleEntity != null){
            return this.roleMapper.toRoleDto(roleEntity);
        }
        return null;
    }
}
