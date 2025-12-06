package com.example.cse_backend.repository;

import com.example.cse_backend.Dto.UserDto;
import com.example.cse_backend.Entity.UserEntity;
import jakarta.persistence.criteria.From;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT COUNT(u.id) FROM UserEntity u WHERE u.userType = true")
    Long countAdminStaff();

    @Query("SELECT COUNT(u.id) FROM UserEntity u WHERE u.userType = false")
    Long countStudents();

    @Query("SELECT new com.example.cse_backend.Dto.UserDto(" +
            "u.name, " +
            "u.enrollNo, " +
            "u.email, " +
            "u.mobile, " +
            "u.gender, " +
            "u.address) " +
            "FROM UserEntity u " +
            "WHERE u.userType = false")
    List<UserDto> getAllStudentsBasicInfo();

}