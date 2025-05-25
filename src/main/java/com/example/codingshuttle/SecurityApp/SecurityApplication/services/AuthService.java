package com.example.codingshuttle.SecurityApp.SecurityApplication.services;


import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public String login(LogInDTO loginDTO) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user=(User)authentication.getPrincipal();
        return jwtService.generateTaken(user);

    }
}
