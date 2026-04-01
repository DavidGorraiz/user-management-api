package com.davidgorraiz.userapi.dto.mapper;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User userEntity);
    List<UserDTO> toDtos(Iterable<User> userEntities);

    @InheritInverseConfiguration
    User toUserEntity(UserDTO userDto);
}
