package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.service.UserService;
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

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnAllUsers() throws Exception {

        List<UserDTO> users = List.of(
                new UserDTO(1L, "David", "david@gmail.com", "1234", Boolean.TRUE, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                new UserDTO(2L, "Juan", "juan@gmail.com", "12345", Boolean.TRUE, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now())
        );

        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("David"))
                .andExpect(jsonPath("$[1].username").value("Juan"));
    }
}
