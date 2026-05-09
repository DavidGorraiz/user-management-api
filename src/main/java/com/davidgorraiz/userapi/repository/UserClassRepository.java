package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.UserUpdateDTO;
import com.davidgorraiz.userapi.dto.mapper.UserMapper;
import com.davidgorraiz.userapi.entity.User;
import com.davidgorraiz.userapi.exceptions.UserNotFoundException;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
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
        // If findById is empty, the map method is not executed and returns Optional.empty()
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User userEntity = this.userMapper.toUserEntity(userDTO);
        userEntity.setPassword(userDTO.password());
        userEntity.setId(null);

        return this.userMapper.toUserDto(this.jpaUserRepository.save(userEntity));
    }

    @Override
    public UserDTO updateUser(long id, UserUpdateDTO updateUserDTO) {
        User userFound = this.jpaUserRepository.findById(id).orElse(null);

        if (userFound != null) {
            this.userMapper.updateUser(updateUserDTO, userFound);
            return this.userMapper.toUserDto(this.jpaUserRepository.save(userFound));
        }

        return null;
    }

}
