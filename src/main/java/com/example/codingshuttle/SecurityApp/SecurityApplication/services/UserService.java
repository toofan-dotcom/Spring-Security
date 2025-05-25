package com.example.codingshuttle.SecurityApp.SecurityApplication.services;

import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.UserDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.example.codingshuttle.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.example.codingshuttle.SecurityApp.SecurityApplication.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("User with email "+username+" not found "));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
      Optional<User> user=  userRepository.findByEmail(signUpDTO.getEmail());
      if(user.isPresent()){
      throw new BadCredentialsException("User if already present " + signUpDTO.getEmail());
      }

      User toBeCreatedUser= modelMapper.map(signUpDTO,User.class);
      toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

      User savedUser=userRepository.save(toBeCreatedUser);

      return modelMapper.map(savedUser,UserDTO.class);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with userId "+userId+" not found"));
    }



}
