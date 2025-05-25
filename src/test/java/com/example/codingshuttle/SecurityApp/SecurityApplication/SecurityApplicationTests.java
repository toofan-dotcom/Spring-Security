package com.example.codingshuttle.SecurityApp.SecurityApplication;

import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {


	@Autowired
	JwtService jwtService;

	@Test
	void contextLoads() {
	User user=new User(4L,"abhi@gmail.com","password","abhishek");
	String token= jwtService.generateTaken(user);

	System.out.println(token);
	Long id= jwtService.getUserIdFromToken(token);
    System.out.println(id);

	}

}
