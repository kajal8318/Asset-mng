package com._eq.asset_management_system.user.mapper;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.user.dto.UserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;
import com._eq.asset_management_system.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User toEntity(UserRequestDto request, Employee employee) {

        User user = new User();

        user.setEmployee(employee);
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setIsActive(request.getIsActive());

        return user;
    }

    // Entity -> ResponseDTO
    public UserResponseDto toResponseDto(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setEmployeeId(user.getEmployee().getId());

        response.setEmployeeName(
                user.getEmployee().getFirstName() + " " +
                        user.getEmployee().getLastName());

        response.setRole(user.getRole());
        response.setIsActive(user.getIsActive());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    // RequestDTO -> Existing Entity (Update)
    public void updateEntity(UserRequestDto request, User user) {

        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setIsActive(request.getIsActive());
    }
}
