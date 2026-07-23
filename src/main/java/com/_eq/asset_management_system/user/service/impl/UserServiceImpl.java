package com._eq.asset_management_system.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.employee.repository.EmployeeRepository;
import com._eq.asset_management_system.user.dto.UserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;
import com._eq.asset_management_system.user.entity.User;
import com._eq.asset_management_system.user.mapper.UserMapper;
import com._eq.asset_management_system.user.repository.UserRepository;
import com._eq.asset_management_system.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EmployeeRepository employeeRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        if (userRepository.findByEmployee(employee).isPresent()) {
            throw new RuntimeException("User already exists for this employee");
        }

        User user = userMapper.toEntity(request, employee);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findByIsActive(true);

        List<UserResponseDto> responseList = new ArrayList<>();

        for (User user : users) {

            responseList.add(userMapper.toResponseDto(user));

        }

        return responseList;
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository
                .findByIdAndIsActive(id, true)
                .orElseThrow(() ->
                        new RuntimeException("Active user not found"));

        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto request) {

        User user = userRepository
                .findByIdAndIsActive(id, true)
                .orElseThrow(() ->
                        new RuntimeException("Active user not found"));

        userMapper.updateEntity(request, user);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository
                .findByIdAndIsActive(id, true)
                .orElseThrow(() ->
                        new RuntimeException("Active user not found"));

        user.setIsActive(false);

        userRepository.save(user);
    }
}