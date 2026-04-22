package com.davidgorraiz.userapi.repository.JpaRepositories;

import com.davidgorraiz.userapi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRoleReposiory extends JpaRepository<UserRole, Long> {
}
