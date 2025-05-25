package com.example.codingshuttle.SecurityApp.SecurityApplication.controllers;


import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.dto.UserDTO;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.AuthService;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping("/signup")

    public ResponseEntity<UserDTO>  SignUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO=userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogInDTO loginDTO){
        String token=authService .login(loginDTO);
        return ResponseEntity.ok(token);
    }


}
