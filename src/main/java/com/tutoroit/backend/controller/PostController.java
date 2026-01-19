// package com.tutoroit.backend.controller;

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import com.tutoroit.backend.model.Post;
// import com.tutoroit.backend.service.PostService;

// @RestController
// @RequestMapping("/api/posts")
// @CrossOrigin(origins = "http://localhost:5173")
// public class PostController {

//     @Autowired
//     private PostService postService;

//     // ✅ CREATE POST
//     @PostMapping("/create")
//     @CrossOrigin(origins = "http://localhost:5173")
//     public String createPost(
//             @RequestBody Post post,
//             @RequestParam String tutorEmail) {

//         return postService.createPost(post, tutorEmail);
//     }

//     // ✅ GET ALL POSTS (IMPORTANT FIX)
//     @GetMapping
//     @CrossOrigin(origins = "http://localhost:5173")
//     public List<Post> getAllPosts() {
//         return postService.getAllPosts();
//     }
//     @DeleteMapping("/delete/{id}")
//     @CrossOrigin(origins = "http://localhost:5173")
//     public String deletePost(@PathVariable Long id) {
//         postService.deletePostById(id);


package com.tutoroit.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutoroit.backend.model.Post;
import com.tutoroit.backend.service.PostService;
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*") // ✅ allow Vercel + local
public class PostController {

    @Autowired
    private PostService postService;

    // ✅ CREATE POST (FINAL FIX)
    @PostMapping
    public String createPost(@RequestBody Post post) {
        // tutorEmail ab BODY se aayega
        return postService.createPost(post, post.getTutorEmail());
    }

    // ✅ GET ALL POSTS
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // ✅ DELETE POST
    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return "Post deleted";
    }
}

