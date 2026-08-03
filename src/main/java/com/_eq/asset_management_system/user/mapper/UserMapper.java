package com._eq.asset_management_system.user.mapper;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.user.dto.CreateUserRequestDto;
import com._eq.asset_management_system.user.dto.UpdateUserRequestDto;
import com._eq.asset_management_system.user.dto.UserResponseDto;
import com._eq.asset_management_system.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User toEntity(CreateUserRequestDto request, Employee employee) {

        User user = new User();

        user.setEmployee(employee);
        user.setRole(request.getRole());
        user.setIsActive(true);

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

        response.setEmployeeEmail(user.getEmployee().getEmail());

        response.setFirebaseUid(user.getFirebaseUid());

        response.setRole(user.getRole());

        response.setIsActive(user.getIsActive());



        return response;
    }

    // RequestDTO -> Existing Entity (Update)
    public void updateEntity(UpdateUserRequestDto request, User user) {

        user.setRole(request.getRole());


    }
}
