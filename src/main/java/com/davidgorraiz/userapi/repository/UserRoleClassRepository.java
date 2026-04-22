package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.dto.mapper.UserRoleMapper;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRoleReposiory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleClassRepository implements UserRoleRepository{
    private final UserRoleMapper userRoleMapper;
    private final JpaUserRoleReposiory jpaUserRoleReposiory;

    public UserRoleClassRepository(UserRoleMapper userRoleMapper,
                                   JpaUserRoleReposiory jpaUserRoleReposiory) {
        this.userRoleMapper = userRoleMapper;
        this.jpaUserRoleReposiory = jpaUserRoleReposiory;
    }

    @Override
    public List<UserRoleDTO> getAll() {
        return this.userRoleMapper.toDtos(this.jpaUserRoleReposiory.findAll());
    }
}
