package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.UserTestData;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnAllUsers(){
        // Arrange
        List<UserDTO> users = List.of(
                UserTestData.createDefaultUserDto("David", "david@gmail.com"),
                UserTestData.createDefaultUserDto("juan", "juan@gmia.com")
        );

        when(userRepository.getAll()).thenReturn(users);

        // Act (ejecutar)
        List<UserDTO> result = userService.getAll();
        System.out.println(result);

        // Assert (verificar)
        assertEquals(2, result.size());
        assertEquals("David", result.get(0).username());

        verify(userRepository).getAll();
    }
}
