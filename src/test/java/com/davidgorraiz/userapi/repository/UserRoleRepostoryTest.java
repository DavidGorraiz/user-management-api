package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.entity.Role;
import com.davidgorraiz.userapi.entity.User;
import com.davidgorraiz.userapi.entity.UserRole;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaRoleRepository;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRepository;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaUserRoleReposiory;
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
public class UserRoleRepostoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private JpaUserRoleReposiory jpaUserRoleReposiory;
    @Autowired
    private JpaRoleRepository jpaRoleRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;


    @Test
    void shouldFinaAllUserRoles(){
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

        Role role1 = new Role();
        role1.setName("Admin");
        Role role2 = new Role();
        role2.setName("User");

        jpaRoleRepository.save(role1);
        jpaRoleRepository.save(role2);

        UserRole userRole1 = new UserRole();
        userRole1.setUser(user1);
        userRole1.setRole(role1);
        userRole1.setUserId(1L);
        userRole1.setRoleId(1L);
        userRole1.setAssignedAt(LocalDateTime.now());
        userRole1.setAssignedBy(1L);
        userRole1.setRevokedAt(null);

        UserRole userRole2 = new UserRole();
        userRole2.setUser(user2);
        userRole2.setRole(role2);
        userRole2.setAssignedAt(LocalDateTime.now());
        userRole2.setAssignedBy(1L);
        userRole2.setRevokedAt(null);

        jpaUserRoleReposiory.save(userRole1);
        jpaUserRoleReposiory.save(userRole2);

        // Act
        List<UserRoleDTO> userRoles = userRoleRepository.getAll();
        System.out.println(userRoles);

        // Assert
        assertThat(userRoles).isNotEmpty();
        assertThat(userRoles.size()).isEqualTo(2);
    }
}
