package com.davidgorraiz.userapi.repository.JpaRepositories;

import com.davidgorraiz.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
