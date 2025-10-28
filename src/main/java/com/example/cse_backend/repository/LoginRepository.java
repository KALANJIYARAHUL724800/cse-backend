package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}