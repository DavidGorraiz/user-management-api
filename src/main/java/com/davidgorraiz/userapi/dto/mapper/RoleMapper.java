package com.davidgorraiz.userapi.dto.mapper;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDto(Role roleEntity);
    List<RoleDTO> toDtos(Iterable<Role> roleEntities);

    @InheritInverseConfiguration
    Role toRoleEntity(RoleDTO roleDTO);
}
