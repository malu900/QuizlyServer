package com.example.quizly.models.request.authentication;

public class RegisterModel {
    private String name;
    private String email;
    private String password;
    private String secondPassword;

    public RegisterModel(String name, String email, String password, String secondPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.secondPassword = secondPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }
}
