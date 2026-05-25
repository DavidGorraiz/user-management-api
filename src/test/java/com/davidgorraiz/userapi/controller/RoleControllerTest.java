package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;
import com.davidgorraiz.userapi.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @Test
    void shouldCreateRole() throws Exception {
        // Simular datos de entrada y salida
        RoleDTO roleToCreate = new RoleDTO(1L, "NEW ROLE");

        // Simular el comportamiento del servicio
        when(roleService.createRole(roleToCreate)).thenReturn(roleToCreate);

        // Transformar el rol en json
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonContent = mapper.writeValueAsString(roleToCreate);

        // Simular el endpoint con los datos
        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated());

    }
    @Test
    void shouldUpdateRole() throws Exception {
        // Simular datos de entrada y salida
        RoleUpdateDTO input = new RoleUpdateDTO("NEW ROLE");
        RoleDTO roleUpdated = new RoleDTO(roles.get(0).id(), input.name());

        // Simular lo que devuelve el servicio
        when(roleService.updateRole(roles.get(0).id(), input)).thenReturn(roleUpdated);

        // Transformar la entrada en json para el body
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonContent = mapper.writeValueAsString(input);

        // Verificar que el endpoint funciona
        mockMvc.perform(put("/roles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
