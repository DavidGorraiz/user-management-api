package com.davidgorraiz.userapi.dto.mapper;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;
import com.davidgorraiz.userapi.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDto(Role roleEntity);
    List<RoleDTO> toDtos(Iterable<Role> roleEntities);

    @InheritInverseConfiguration
    Role toRoleEntity(RoleDTO roleDTO);

    void updateRoleByDTO(RoleUpdateDTO roleDTO, @MappingTarget Role role);
}
