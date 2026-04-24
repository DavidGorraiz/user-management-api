package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    void shoulReturnAllUserRoles(){
        // Arrange
        List<UserRoleDTO> userRoles = List.of(
                new UserRoleDTO(1L,
                        new UserDTO(1L,"david","david@gmal.com",Boolean.TRUE, LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now()),
                        new RoleDTO(1L, "ADMIN"),
                        LocalDateTime.now(), 1L, null),
                new UserRoleDTO(2L,
                        new UserDTO(2L,"juan","juan@gmal.com",Boolean.TRUE, LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now()),
                        new RoleDTO(2L, "USER"),
                        LocalDateTime.now(), 1L, null)

                );

        when(userRoleRepository.getAll()).thenReturn(userRoles);

        // Act
        List<UserRoleDTO> result = userRoleService.getAll();
        System.out.println(result);

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());

        verify(userRoleRepository).getAll();
    }
}
