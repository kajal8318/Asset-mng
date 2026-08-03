package com._eq.asset_management_system.user.contoller;


import java.util.List;

import com._eq.asset_management_system.user.dto.UpdateUserRequestDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com._eq.asset_management_system.user.dto.CreateUserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;
import com._eq.asset_management_system.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}