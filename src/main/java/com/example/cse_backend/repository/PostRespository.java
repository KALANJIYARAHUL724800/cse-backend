    package com.example.cse_backend.repository;

    import com.example.cse_backend.Entity.PostEntity;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface PostRespository extends JpaRepository<PostEntity,Long> {
    }
