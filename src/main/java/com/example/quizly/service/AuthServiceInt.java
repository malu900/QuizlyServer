package com.example.quizly.service;

import com.example.quizly.models.request.authentication.GuestModel;
import com.example.quizly.models.request.authentication.LoginModel;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.models.response.Authentication.LoginResponse;

public interface AuthServiceInt {
    String register(RegisterModel user);
    LoginResponse login(LoginModel user);
    String registerGuest(GuestModel guest);
}
