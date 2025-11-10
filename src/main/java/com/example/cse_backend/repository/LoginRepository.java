package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT COUNT(u.id) FROM UserEntity u WHERE u.userType = true")
    Long countAdminStaff();

    @Query("SELECT COUNT(u.id) FROM UserEntity u WHERE u.userType = false")
    Long countStudents();
}