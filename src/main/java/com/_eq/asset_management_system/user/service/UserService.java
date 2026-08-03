package com._eq.asset_management_system.user.service;

import com._eq.asset_management_system.user.dto.CreateUserRequestDto;
import com._eq.asset_management_system.user.dto.UpdateUserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto request);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UpdateUserRequestDto request);

    void deleteUser(Long id);
}
