package com.example.codingshuttle.SecurityApp.SecurityApplication.repositories;

import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
