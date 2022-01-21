package com.example.rng;

public class User {
    private String name, age, email;

    public User() {
    }

    public User(String name){
        this.name = name;
    }

    public User(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAge(){
        return this.age;
    }

    public String getEmail(){
        return this.email;
    }
}

