package com.example.codingshuttle.SecurityApp.SecurityApplication.dto;

public class LogInDTO {
    String email;
    String password;

    public LogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LogInDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
