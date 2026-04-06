package com.davidgorraiz.userapi.repository;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.entity.Role;
import com.davidgorraiz.userapi.repository.JpaRepositories.JpaRoleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JpaRoleRepository jpaRoleRepository;

    @Test
    void shouldFindAllRoles() {
        // Arrange
        Role role1 = new Role();
        role1.setName("Admin");
        Role role2 = new Role();
        role2.setName("User");

        jpaRoleRepository.save(role1);
        jpaRoleRepository.save(role2);

        // Act
        List<RoleDTO> roles = roleRepository.getAll();
        System.out.println(roles);

        // Assert
        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isEqualTo(2);
    }
}
