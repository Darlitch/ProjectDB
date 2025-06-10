package com.db.project.api.controller;

import com.db.project.api.dto.role.RoleDTO;
import com.db.project.api.dto.role.RoleUpdateDTO;
import com.db.project.core.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/{userId}/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @GetMapping
    public RoleDTO get(@PathVariable Integer userId) {
        return userService.getById(userId).getRole();
    }

    @PatchMapping
    public RoleDTO update(@PathVariable Integer userId, @Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
        return userService.updateRole(userId, roleUpdateDTO).getRole();
    }
}
