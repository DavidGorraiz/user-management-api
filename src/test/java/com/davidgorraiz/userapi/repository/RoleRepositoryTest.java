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
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JpaRoleRepository jpaRoleRepository;

    // Arrange
    Role role1 = new Role(null, "ADMIN");
    Role role2 = new Role(null, "USER");

    @Test
    void shouldFindAllRoles() {
        jpaRoleRepository.save(role1);
        jpaRoleRepository.save(role2);

        // Act
        List<RoleDTO> roles = roleRepository.getAll();
        System.out.println(roles);

        // Assert
        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isEqualTo(2);
    }
    @Test
    void shouldReturnRoleById(){
        jpaRoleRepository.save(role1);
        jpaRoleRepository.save(role2);

        Optional<RoleDTO> role = roleRepository.getById(role2.getId());
        System.out.println(role);

        assertThat(role).isNotEmpty();
        assertThat(role.get().name()).isEqualTo(role2.getName());
    }
}
