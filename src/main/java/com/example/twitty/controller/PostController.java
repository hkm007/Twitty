package com.example.twitty.controller;

import com.example.twitty.dao.PostRepository;
import com.example.twitty.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable("postId") Long postId) {
        return postRepository.findById(postId).get();
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody Post post) {
        Post oldPost = postRepository.findById(post.getPostId()).get();
        oldPost.setContent(post.getContent());
        postRepository.save(oldPost);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        postRepository.deleteById(postId);
        return ResponseEntity.ok("Success");
    }
}
