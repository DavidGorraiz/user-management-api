package com.davidgorraiz.userapi.repository.JpaRepositories;

import com.davidgorraiz.userapi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserRoleReposiory extends JpaRepository<UserRole, Long> {
    @Query("""
            SELECT ur FROM UserRole ur WHERE ur.userId = :user_id
            """)
    List<UserRole> findByUserId(@Param("user_id") long id);
}
