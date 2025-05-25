package com.example.codingshuttle.SecurityApp.SecurityApplication.repositories;

import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity,Long> {
}
