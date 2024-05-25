package com.example.aman;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String age;

    public User(String name, String email, String phoneNumber, String age) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAge() {
        return age;
    }
}
