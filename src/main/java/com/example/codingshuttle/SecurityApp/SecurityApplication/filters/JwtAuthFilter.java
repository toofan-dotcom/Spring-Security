package com.example.codingshuttle.SecurityApp.SecurityApplication.filters;

import com.example.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.JwtService;
import com.example.codingshuttle.SecurityApp.SecurityApplication.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader=request.getHeader("Authorization");
        if(requestTokenHeader==null||!requestTokenHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=requestTokenHeader.split("Bearer ")[1];

      Long userId= jwtService.getUserIdFromToken(token);

      if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
       User user= userService.getUserById(userId);
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user,null,null);

          usernamePasswordAuthenticationToken.setDetails(
                  new WebAuthenticationDetailsSource().buildDetails(request)
          );
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

      }
      filterChain.doFilter(request,response);



    }
}
