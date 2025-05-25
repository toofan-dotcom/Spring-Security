package com.example.codingshuttle.SecurityApp.SecurityApplication.dto;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PostDTO { Long id;
String title;
String description;
    public PostDTO() {}

    public PostDTO(String title, String description, Long id) {
        this.title = title;
        this.description = description;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

}
