package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.UserTestData;
import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.entity.User;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Test
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
}
