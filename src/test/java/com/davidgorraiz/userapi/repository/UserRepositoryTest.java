package com.davidgorraiz.userapi.repository;

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
        // Arrange
        User user1 = new User();
        user1.setUsername("david");
        user1.setEmail("david@test.com");
        user1.setPassword("1234");
        user1.setEnabled(true);
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setLastLogin(LocalDateTime.now());

        User user2 = new User();
        user2.setUsername("juan");
        user2.setEmail("juan@test.com");
        user2.setPassword("5678");
        user2.setEnabled(true);
        user2.setCreatedAt(LocalDateTime.now());
        user2.setUpdatedAt(LocalDateTime.now());
        user2.setLastLogin(LocalDateTime.now());

        jpaUserRepository.save(user1);
        jpaUserRepository.save(user2);

        // Act
        List<UserDTO> users = userRepository.getAll();
        System.out.println(users);

        // Assert
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(2);
    }
}
