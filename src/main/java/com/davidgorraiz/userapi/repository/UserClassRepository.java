package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.mapper.UserMapper;
import com.davidgorraiz.userapi.exceptions.UserNotFoundException;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<UserDTO> getById(long id) {
        return this.jpaUserRepository.findById(id)
                .map(userMapper::toUserDto);
        // Wenn findById leer ist, wird die Map-Methode nicht ausgeführt und gibt Optional.empty() zurück
    }
}
