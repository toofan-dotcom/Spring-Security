package com.example.codingshuttle.SecurityApp.SecurityApplication.entities;

import jakarta.persistence.*;

@Entity
@Table(name="posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name="title")
    String title;
    @Column(name="description")
    String description;



    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostEntity(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public PostEntity(){

    }

}
