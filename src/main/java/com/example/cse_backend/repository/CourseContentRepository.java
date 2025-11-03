package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.CourseContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseContentRepository extends JpaRepository<CourseContentEntity,Long> {
    List<CourseContentEntity> findByCourseEntityId(Long courseId);

}
