package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoleService roleService;

    List<RoleDTO> roles = List.of(
            new RoleDTO(1L, "Admin"),
            new RoleDTO(2L, "User")
    );

    @Test
    void shouldReturnAllRoles() throws Exception {

        when(roleService.getAll()).thenReturn(roles);

        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Admin"))
                .andExpect(jsonPath("$[1].name").value("User"));
    }
    @Test
    void shouldReurnRoleBYId() throws Exception {
        when(roleService.getById(roles.get(1).id())).thenReturn(roles.get(1));

        mockMvc.perform(get("/roles/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("User"));
    }
}
