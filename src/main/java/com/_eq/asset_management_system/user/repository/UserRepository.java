package com._eq.asset_management_system.user.repository;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmployee(Employee employee);

    Optional<User> findByFirebaseUidAndIsActive(String firebaseUid, Boolean isActive);

    List<User> findByIsActive(Boolean isActive);

    Optional<User> findByIdAndIsActive(Long id, Boolean isActive);
}
