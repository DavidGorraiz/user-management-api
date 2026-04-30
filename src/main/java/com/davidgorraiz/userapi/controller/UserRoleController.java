package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.UserRoleDTO;
import com.davidgorraiz.userapi.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserRole")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAll(){
        return ResponseEntity.ok(this.userRoleService.getAll());
    }
}
