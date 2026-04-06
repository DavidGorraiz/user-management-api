package com.davidgorraiz.userapi.repository.JpaRepositories;

import com.davidgorraiz.userapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
}
