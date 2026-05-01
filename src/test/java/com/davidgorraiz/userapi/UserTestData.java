package com.davidgorraiz.userapi;

import com.davidgorraiz.userapi.dto.UserDTO;
import com.davidgorraiz.userapi.entity.User;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class UserTestData {

    // The counter starts at 1 and is incremented atomically
    private static final AtomicLong ID_COUNTER = new AtomicLong(1);

    public static User createDefaultUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword("password123"); // Valor por defecto
        user.setEnabled(true);
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setLastLogin(now);
        return user;
    }
    public static UserDTO createDefaultUserDto(String username, String email) {
        UserDTO userdto = new UserDTO(ID_COUNTER.getAndIncrement(), username, email, Boolean.TRUE,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        return userdto;
    }
}
