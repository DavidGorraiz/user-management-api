package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.UserTestData;
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
    void shouldReturUsersRoles() throws Exception {
        // Simular datos
        UserDTO user = UserTestData.createDefaultUserDto("user1", "user1@test.com");

        List<UserRoleDTO> userRoles = List.of(
                new UserRoleDTO(1L,
                        user,
                        new RoleDTO(1L, "ADMIN"),
                        LocalDateTime.now(), 1L, null),
                new UserRoleDTO(2L,
                        user,
                        new RoleDTO(2L, "USER"),
                        LocalDateTime.now(), 1L, null)

        );

        // Simulamos el comportamiento del servicio
        when(userRoleService.getAll(user.id())).thenReturn(userRoles);

        // Llamamos el endpoint del metodo y verificamos que los datos coincidan
        mockMvc.perform(get("/users/1/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].role.name").value(userRoles.get(0).role().name()))
                .andExpect(jsonPath("$[1].role.name").value(userRoles.get(1).role().name()));
    }
}
