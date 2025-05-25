package com.example.codingshuttle.SecurityApp.SecurityApplication.controllers;

import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.PostDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.PostServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

   @GetMapping
    public List<PostDTO> getAllPosts(){
       return postServiceImpl.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postServiceImpl.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
       return postServiceImpl.createNewPost(inputPost);
    }

    @PutMapping("/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId){
        return postServiceImpl.updatePost(inputPost,postId);

    }
}
