package com.example.shareyourfoodapp.model;

public class Comment {
    private Integer id;
    private String author_mail;
    private Integer id_recipe;
    private String text;

    public Comment(Integer id, String author_mail, Integer id_recipe, String text) {
        this.id = id;
        this.author_mail = author_mail;
        this.id_recipe = id_recipe;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor_mail() {
        return author_mail;
    }

    public Integer getId_recipe() {
        return id_recipe;
    }

    public String getText() {
        return text;
    }
}
