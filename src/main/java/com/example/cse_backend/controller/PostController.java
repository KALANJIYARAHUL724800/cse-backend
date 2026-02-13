package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.PostDto;
import com.example.cse_backend.Entity.PostEntity;
import com.example.cse_backend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody PostDto data, BindingResult result)
    {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return new ResponseEntity<>(postService.insert(data), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> show()
    {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            PostEntity post = postService.findOne(id);
            return ResponseEntity.ok(post);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody PostDto data,
            BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            PostEntity updatedPost = postService.update(id, data);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/count-posts")
    public Long getPostCount(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        Long count = postService.countPostsByDate(localDate);
        return count != null ? count : 0L;
    }

}
