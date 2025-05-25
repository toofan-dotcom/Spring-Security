package com.example.codingshuttle.SecurityApp.SecurityApplication.advice;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    LocalDateTime timestamp;
    String error;
    HttpStatus statusCode;

//    public ApiError(String error, HttpStatus statusCode, LocalDateTime timestamp) {
//        this.error = error;
//        this.statusCode = statusCode;
//        this.timestamp = timestamp;
//    }

    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
