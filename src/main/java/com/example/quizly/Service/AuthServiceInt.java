package com.example.quizly.Service;

import com.example.quizly.Models.request.Authentication.LoginModel;
import com.example.quizly.Models.request.Authentication.RegisterModel;
import com.example.quizly.Models.response.Authentication.LoginResponse;
import com.example.quizly.accessingData.User;

public interface AuthServiceInt {
    String register(RegisterModel user);
    LoginResponse login(LoginModel user);
}
