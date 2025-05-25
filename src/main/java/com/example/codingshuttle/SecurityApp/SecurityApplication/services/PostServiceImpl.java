package com.example.codingshuttle.SecurityApp.SecurityApplication.services;

import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.PostDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.PostEntity;
import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.example.codingshuttle.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.example.codingshuttle.SecurityApp.SecurityApplication.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl {
    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity ->modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long postId){
     User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//             log.info("user {}",user);
        PostEntity postEntity=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id "+postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }


    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity=modelMapper.map(inputPost,PostEntity.class);
      return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }

    public PostDTO updatePost(PostDTO inputPost ,Long postId){
        PostEntity olderPost=postRepository.
                findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post not found with id "+postId));
          inputPost.setId(postId);
          modelMapper.map(inputPost,olderPost);
        PostEntity savedPostEntity=postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity,PostDTO.class);
    }
}
