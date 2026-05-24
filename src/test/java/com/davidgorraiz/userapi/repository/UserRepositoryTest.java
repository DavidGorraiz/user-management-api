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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<UserDTO> userDto = userRepository.getById(savedUsers.get(1).getId());
        System.out.println("ID buscado: " + userDto.get().id());
        System.out.println("Usuario encontrado: " + userDto);

        // Assert
        assertThat(userDto).isNotNull();
        assertThat(userDto.get().username()).isEqualTo("juan");
    }
    @Test
    @DisplayName("It must return an empty Optional if the ID does not exist")
    void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
        // Act
        Optional<UserDTO> result = userRepository.getById(999L);

        // Assert
        assertThat(result).isEmpty();
    }
    @Test
    @DisplayName("It must return the created user")
    void shouldCreateUser() {
        // Simular datos
        UserDTO userToCreate = new UserDTO(null, "NEW", "new@test.com", "1234",
                Boolean.TRUE, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        System.out.println(userToCreate);
        // Usar el metodo para crear usuario
        UserDTO result = userRepository.createUser(userToCreate);
        System.out.println(result);

        // Verificar que el usuario se crear en la base de datos
        assertThat(result.username()).isEqualTo(jpaUserRepository.findById(result.id()).get().getUsername());
    }
    @Test
    @DisplayName("It must update user")
    void shouldUpdateUser() {
        UserDTO user = userRepository.createUser(UserTestData.createDefaultUserDto("Jose", "jose@test.com"));
        Optional<UserDTO> userFound = userRepository.getById(user.id());
        System.out.println(userFound);

        assertThat(userFound.get().username()).isEqualTo("Jose");

        userRepository.updateUser(user.id(),
                UserTestData.createDefaultUpdateUserDto("Pablo", "pablo@test.com"));

        userFound = userRepository.getById(user.id());
        System.out.println(userFound);

        assertThat(userFound.get().username()).isEqualTo("Pablo");
    }
    @Test
    @DisplayName("It must delete a useer")
    void shouldDeleteUser(){
        UserDTO userCreated = userRepository.createUser(UserTestData.createDefaultUserDto(
                "Maria", "maria@test.com"));
        Optional<UserDTO> userFound = userRepository.getById(userCreated.id());

        assertThat(userFound.get().username()).isEqualTo("Maria");

        UserDTO userDeleted = userRepository.deleteUser(userFound.get().id());
        System.out.println(userDeleted);

        userFound = userRepository.getById(userDeleted.id());
        assertThat(userFound).isEmpty();
    }
}
