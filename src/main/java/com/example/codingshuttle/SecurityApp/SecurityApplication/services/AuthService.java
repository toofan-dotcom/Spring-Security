package com.example.codingshuttle.SecurityApp.SecurityApplication.services;


import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LoginResponseDTO;
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
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponseDTO login(LogInDTO loginDTO) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user=(User)authentication.getPrincipal();
        String accessToken=jwtService.generateAccessTaken(user);
        String refreshToken= jwtService.generateRefreshTaken(user);

        return new LoginResponseDTO(user.getId(), accessToken,refreshToken);

    }

    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId= jwtService.getUserIdFromToken(refreshToken);
        User user=userService.getUserById(userId);
        String accessToken=jwtService.generateAccessTaken(user);
        return  new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }
}
