package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.mapper.UserMapper;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserClassRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserClassRepository(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userMapper.toDtos(this.jpaUserRepository.findAll());
    }

    @Override
    public UserDTO getById(long id) {
        return this.userMapper.toUserDto(this.jpaUserRepository.findById(id).orElse(null));
    }
}
