package com.projekat.rent.model;

public class User {

    private Long id;

    private String name;

    private String email;

    private String password;


    public User(String name,  String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
