package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CommentsDto;
import com.example.cse_backend.Dto.PostCommentsResponseDto;
import com.example.cse_backend.Entity.CommentsEntity;
import com.example.cse_backend.services.CommentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
@CrossOrigin("*")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody CommentsDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            CommentsEntity savedComment = commentsService.insert(data);
            return ResponseEntity.ok(savedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> show() {
        return ResponseEntity.ok(commentsService.show());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            CommentsEntity comment = commentsService.find(id);
            return ResponseEntity.ok(comment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody CommentsDto data,
                                    BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            CommentsEntity updatedComment = commentsService.update(id, data);
            return ResponseEntity.ok(updatedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsForPost(@PathVariable Long postId) {
        List<PostCommentsResponseDto> comments = commentsService.getCommentsByPostId(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No comments found for post with id: " + postId);
        }
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/likes/update")
    public ResponseEntity<?> updateLikes(@RequestParam Long id, @RequestBody CommentsDto data) {
        return new ResponseEntity<>(commentsService.updateLikes(id, data), HttpStatus.OK);
    }
    @GetMapping("/{postId}/likes/total")
    public ResponseEntity<Long> getTotalLikes(@PathVariable Long postId) {
        Long totalLikes = commentsService.getTotalLikesByPostId(postId);
        if (totalLikes == null) {
            totalLikes = 0L;
        }
        return ResponseEntity.ok(totalLikes);
    }
}
