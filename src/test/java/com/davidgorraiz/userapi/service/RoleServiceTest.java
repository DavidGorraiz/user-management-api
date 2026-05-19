package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.exceptions.RoleNotFoundException;
import com.davidgorraiz.userapi.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    // Arrange
    List<RoleDTO> roles = List.of(
            new RoleDTO(1L, "Admin"),
            new RoleDTO(2L, "User")
    );

    @Test
    void shouldReturnAllRoles(){
        when(roleRepository.getAll()).thenReturn(roles);

        // Act
        List<RoleDTO> result = roleService.getAll();
        System.out.println(result);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Admin", result.get(0).name());

        verify(roleRepository).getAll();
    }
    @Test
    void shouldReturnRoleById(){
        when(roleRepository.getById(roles.get(1).id())).thenReturn(Optional.ofNullable((roles.get(1))));

        RoleDTO result = roleService.getById(roles.get(1).id());
        System.out.println(result);

        assertThat(result).isNotNull();
        assertEquals(result.name(), roles.get(1).name());

        verify(roleRepository).getById(roles.get(1).id());
    }
    @Test
    void shouldReturnErrorNotFound(){
        long id = 999L;
        when(roleRepository.getById(id)).thenReturn(Optional.empty());

        RoleNotFoundException ex = assertThrows(RoleNotFoundException.class,
                () -> roleService.getById(id));
        System.out.println(ex);

        assertEquals("Role with id: 999 not found", ex.getMessage());
    }
}
