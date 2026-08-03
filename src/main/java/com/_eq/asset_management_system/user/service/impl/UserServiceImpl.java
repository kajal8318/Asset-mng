package com._eq.asset_management_system.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import com._eq.asset_management_system.common.exception.AlreadyExistsException;
import com._eq.asset_management_system.common.exception.ResourceNotFoundException;
import com._eq.asset_management_system.security.service.FirebaseAuthenticationService;
import com._eq.asset_management_system.user.dto.UpdateUserRequestDto;
import org.springframework.stereotype.Service;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.employee.repository.EmployeeRepository;
import com._eq.asset_management_system.user.dto.CreateUserRequestDto;
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

    private final FirebaseAuthenticationService firebaseAuthenticationService;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (userRepository.findByEmployee(employee).isPresent()) {
            throw new AlreadyExistsException("User already exists for this employee");
        }

        String firebaseUid = firebaseAuthenticationService.createUser(
                employee.getEmail(),
                request.getPassword()
        );

        User user = userMapper.toEntity(request, employee);

        user.setFirebaseUid(firebaseUid);

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
                        new ResourceNotFoundException("Active User not found"));

        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto request) {

        User user = userRepository
                .findByIdAndIsActive(id, true)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Active User not found"));

        userMapper.updateEntity(request, user);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository
                .findByIdAndIsActive(id, true)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Active User not found"));

        user.setIsActive(false);

        userRepository.save(user);
    }

}