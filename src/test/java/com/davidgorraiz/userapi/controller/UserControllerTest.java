package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.UserTestData;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnAllUsers() throws Exception {

        List<UserDTO> users = List.of(
                UserTestData.createDefaultUserDto("David", "david@gmail.com"),
                UserTestData.createDefaultUserDto("Juan", "juan@gmia.com")
        );

        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("David"))
                .andExpect(jsonPath("$[1].username").value("Juan"));
    }
    @Test
    void shouldReturnUserById() throws Exception {
        List<UserDTO> users = List.of(
                UserTestData.createDefaultUserDto("David", "david@gmail.com"),
                UserTestData.createDefaultUserDto("Juan", "juan@gmia.com")
        );

        when(userService.getById(1)).thenReturn(users.get(0));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("david@gmail.com"));
    }
    @Test
    void shouldCreateUser() throws Exception {
        UserDTO input = UserTestData.createDefaultUserDto("David", "david@test.co");
        UserDTO saved = UserTestData.createDefaultUserDto("David", "david@test.co");

        when(userService.createUser(input)).thenReturn(saved);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonContent = mapper.writeValueAsString(input);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());

    }
}
