package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.UserTestData;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.dto.UserUpdateDTO;
import com.davidgorraiz.userapi.exceptions.UserNotFoundException;
import com.davidgorraiz.userapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Test
    void shouldReturnUserById(){
        // Arrange
        List<UserDTO> users = List.of(
                UserTestData.createDefaultUserDto("David", "david@gmail.com"),
                UserTestData.createDefaultUserDto("juan", "juan@gmia.com")
        );

        when(userRepository.getById(2)).thenReturn(Optional.ofNullable(users.get(1)));

        //Act
        UserDTO result = userService.getById(2);
        System.out.println(result);

        // Assert
        assertThat(result).isNotNull();
        assertEquals("juan", result.username());
        verify(userRepository).getById(2);
    }
    @Test
    @DisplayName("It should throw a UserNotFoundException when the ID does not exist")
    void shouldThrowExceptionInService() {
        // Arrange
        Long id = 999L;
        // Simulamos que el repo devuelve vacío
        when(userRepository.getById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.getById(id));
    }
    @Test
    @DisplayName("It should create a new user from service")
    void shouldCreateUserSuccessfully() {
        // Given
        UserDTO input = UserTestData.createDefaultUserDto("David", "david@gmail.com");
        UserDTO saved = UserTestData.createDefaultUserDto("David", "david@gmail.com");

        when(userRepository.createUser(input)).thenReturn(saved);

        // When
        UserDTO result = userService.createUser(input);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("David");

        verify(userRepository).createUser(input);
    }
    @Test
    @DisplayName("It should update a user from service")
    void shouldUpdateUser(){
        UserDTO userFound = UserTestData.createDefaultUserDto("Sofia", "sofia@test.co");
        UserUpdateDTO userUpdate = UserTestData.createDefaultUpdateUserDto("Laura", "laura@test.co");
        UserDTO userUpdated = new UserDTO(userFound.id(), "Laura", "laura@test.co",
                "1234", Boolean.TRUE, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        when(userRepository.getById(userFound.id())).thenReturn(Optional.of(userFound));
        when(userRepository.updateUser(userFound.id(), userUpdate)).thenReturn(userUpdated);

        UserDTO result = userService.updateUser(userFound.id(), userUpdate);

        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("Laura");

        verify(userRepository).getById(userFound.id());
        verify(userRepository).updateUser(userFound.id(), userUpdate);
    }
}
