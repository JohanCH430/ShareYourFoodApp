package com.example.shareyourfoodapp.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    private Integer id;
    private String author_mail;
    private String title;
    private String image_url;
    private String description;

    public Recipe(Integer id, String author_mail, String title, String image_url, String description) {
        this.id = id;
        this.author_mail = author_mail;
        this.title = title;
        this.image_url = image_url;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor_mail() {
        return author_mail;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }
}
