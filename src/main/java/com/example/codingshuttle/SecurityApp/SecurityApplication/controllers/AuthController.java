package com.example.codingshuttle.SecurityApp.SecurityApplication.controllers;


import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LoginResponseDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.UserDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.AuthService;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @Value("${deploy.env}")
    String deployEnv;

    @PostMapping("/signup")

    public ResponseEntity<UserDTO>  SignUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO=userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LogInDTO loginDTO, HttpServletRequest request,
                                        HttpServletResponse response){
        LoginResponseDTO loginResponseDTO=authService.login(loginDTO);
        Cookie cookie=new Cookie("refreshToken",loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("prod".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);

    }


    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
    String refreshToken=    Arrays.stream(request.getCookies())
                .filter(cookie->"refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthorizationServiceException("Refresh token not found inside then cookie"));

         LoginResponseDTO loginResponseDTO= authService.refreshToken(refreshToken);


         return ResponseEntity.ok(loginResponseDTO);
    }


}
