package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Long> {
}
