package com.example.cse_backend.repository;

import com.example.cse_backend.Dto.PostCommentsResponseDto;
import com.example.cse_backend.Entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {
    @Query("SELECT new com.example.cse_backend.Dto.PostCommentsResponseDto(" +
            "p.title, p.imageUrl, c.likes, c.comments) " +
            "FROM CommentsEntity c JOIN c.post p " +
            "WHERE p.id = :postId")
    List<PostCommentsResponseDto> findCommentsByPostId(@Param("postId") Long postId);
    @Query("SELECT SUM(c.likes) FROM CommentsEntity c WHERE c.post.id = :postId")
    Long getTotalLikesByPostId(@Param("postId") Long postId);
}
