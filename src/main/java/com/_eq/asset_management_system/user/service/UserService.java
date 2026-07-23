package com._eq.asset_management_system.user.service;

import com._eq.asset_management_system.user.dto.UserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto request);

    void deleteUser(Long id);
}
