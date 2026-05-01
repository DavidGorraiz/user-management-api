package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.UserTestData;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.entity.User;
import com.davidgorraiz.userapi.exceptions.UserNotFoundException;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@Nested
@DisplayName("User Repository Tests")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Test
    @DisplayName("It must return all users")
    void shouldFindAllUsers() {
        jpaUserRepository.save(UserTestData.createDefaultUser("david", "david@test.com"));
        jpaUserRepository.save(UserTestData.createDefaultUser("juan", "juan@test.com"));

        // Act
        List<UserDTO> users = userRepository.getAll();
        System.out.println(users);

        // Assert
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("It should return the DTO if the user exists")
    void shouldFindUserById() {
        // 1. We prepare the data
        List<User> usersToSave = List.of(
                UserTestData.createDefaultUser("david", "david@test.com"),
                UserTestData.createDefaultUser("juan", "juan@test.com")
        );

        // 2. We save and CAPTURE the persisted entity (which already contains the actual ID)
        List<User> savedUsers = List.of(
                jpaUserRepository.save(usersToSave.get(0)),
                jpaUserRepository.save(usersToSave.get(1))
        );

        // 3. Note: We use the actual ID provided by the database
        UserDTO userDto = userRepository.getById(savedUsers.get(1).getId());
        System.out.println("ID buscado: " + userDto.id());
        System.out.println("Usuario encontrado: " + userDto);

        // Assert
        assertThat(userDto).isNotNull();
        assertThat(userDto.username()).isEqualTo("juan");
    }
    @Test
    @DisplayName("It should throw an UserNotFoundException when the ID does not exist")
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange: We define an ID that doesn't exist (999 is usually safe in test environments)
        Long nonExistentId = 999L;

        // Act & Assert: We verify that calling the method triggers the expected exception
        UserNotFoundException exception = // We catch the Spring exception
                assertThrows(UserNotFoundException.class, () -> {
                    userRepository.getById(nonExistentId);
                });

        // Optional: Verify that the exception message is correct
        assertThat(exception.getMessage()).contains("not found");
    }
}
