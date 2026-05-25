package com.davidgorraiz.userapi.controller;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;
import com.davidgorraiz.userapi.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll(){
        return ResponseEntity.ok(this.roleService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(this.roleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO role){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.createRole(role));
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable long id, @RequestBody RoleUpdateDTO roleUpdateDTO){
        return ResponseEntity.ok(this.roleService.updateRole(id, roleUpdateDTO));
    }
}
