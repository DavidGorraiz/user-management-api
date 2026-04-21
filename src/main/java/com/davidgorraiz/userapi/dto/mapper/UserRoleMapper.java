package com.davidgorraiz.userapi.dto.mapper;

import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.entity.UserRole;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO toUserRoleDto(UserRole userRoleEntity);
    List<UserRoleDTO> toDtos(Iterable<UserRole> userRolesEntities);

    @InheritInverseConfiguration
    UserRole toUserRoleEntity(UserRoleDTO userRoleDTO);
}
