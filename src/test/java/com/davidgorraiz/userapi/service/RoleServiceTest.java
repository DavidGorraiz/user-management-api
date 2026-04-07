package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void shouldReturnAllRoles(){
        // Arrange
        List<RoleDTO> roles = List.of(
                new RoleDTO(1L, "Admin"),
                new RoleDTO(2L, "User")
        );

        when(roleRepository.getAll()).thenReturn(roles);

        // Act
        List<RoleDTO> result = roleService.getAll();
        System.out.println(result);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Admin", result.get(0).name());

        verify(roleRepository).getAll();
    }
}
