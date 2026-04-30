package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

@WebMvcTest(UserRoleController.class)
public class UserRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRoleService userRoleService;

    @Test
    void shouldReturnAllUserRoles() throws Exception {
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

        when(userRoleService.getAll()).thenReturn(userRoles);

        mockMvc.perform(get("/UserRole"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));
    }
}
