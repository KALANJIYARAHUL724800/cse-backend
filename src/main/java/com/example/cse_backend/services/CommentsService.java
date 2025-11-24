package com.example.cse_backend.services;

import com.example.cse_backend.Dto.CommentsDto;
import com.example.cse_backend.Dto.PostCommentsResponseDto;
import com.example.cse_backend.Entity.CommentsEntity;
import com.example.cse_backend.Entity.PostEntity;
import com.example.cse_backend.repository.CommentsRepository;
import com.example.cse_backend.repository.PostRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostRespository postRepository;

    public CommentsEntity insert(Long postId, CommentsEntity data) {

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        CommentsEntity obj = new CommentsEntity();
        obj.setComments(data.getComments());
        obj.setLikes(data.getLikes() == null ? 0 : data.getLikes());

        obj.setPost(post);

        return commentsRepository.save(obj);
    }


    public List<CommentsEntity> show() {
        return commentsRepository.findAll();
    }
    public CommentsEntity find(Long id) {
        return commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }
    public CommentsEntity update(Long id, CommentsDto data) {
        CommentsEntity obj = commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        PostEntity post = postRepository.findById(data.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + data.getPostId()));

        obj.setLikes(data.getLikes());
        obj.setComments(data.getComments());
        obj.setPost(post);
        return commentsRepository.save(obj);
    }
    public List<PostCommentsResponseDto> getCommentsByPostId(Long postId) {
        return commentsRepository.findCommentsByPostId(postId);
    }

    public CommentsEntity updateLikes(Long id,CommentsDto data)
    {
        CommentsEntity obj = commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        obj.setLikes(data.getLikes());
        return commentsRepository.save(obj);
    }
    public CommentsEntity updateComments(Long id,CommentsDto data)
    {
        CommentsEntity obj = commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        obj.setComments(data.getComments());
        return commentsRepository.save(obj);
    }
    public Long getTotalLikesByPostId(Long postId) {
        return commentsRepository.getTotalLikesByPostId(postId);
    }
}
