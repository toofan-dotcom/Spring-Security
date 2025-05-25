package com.example.codingshuttle.SecurityApp.SecurityApplication.config;


import com.example.codingshuttle.SecurityApp.SecurityApplication.filters.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    JwtAuthFilter jwtAuthFilter;

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
             httpSecurity
                     .authorizeHttpRequests(auth->auth
                             .requestMatchers("/posts","/auth/**").permitAll()
//                             .requestMatchers("/posts/**")
                             .anyRequest().authenticated())
                     .csrf(csrfConfig->csrfConfig.disable())
                     .sessionManagement(sessionConfig->sessionConfig
                             .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                     .formLogin(Customizer.withDefaults());
             return httpSecurity.build();

   }

//   @Bean
//    UserDetailsService myInMemoryUserDetailService() {
//       UserDetails normalUser = User
//               .withUsername("king")
//               .password(passwordEncoder().encode("password"))
//               .roles("USER")
//               .build();
//
//       UserDetails adminUser=User
//               .withUsername("ranjan")
//               .password(passwordEncoder().encode("password1"))
//               .roles("MANAGER")
//               .build();
//return new InMemoryUserDetailsManager(normalUser,adminUser);
//
//   }




   @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
     return authenticationConfiguration.getAuthenticationManager();
   }


}
