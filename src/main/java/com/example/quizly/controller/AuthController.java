package com.example.quizly.controller;

import com.example.quizly.Models.request.Authentication.GuestModel;
import com.example.quizly.Models.request.Authentication.LoginModel;
import com.example.quizly.Models.request.Authentication.RegisterModel;
import com.example.quizly.Models.response.Authentication.LoginResponse;
import com.example.quizly.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody RegisterModel user){
        if(user.getPassword().equals(user.getSecondPassword())){
            return authService.register(user);
        }
        else {
            return "unsuccesfull";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginModel user){
        LoginResponse returnValue = authService.login(user);
        return returnValue;

    }
    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    public String registerGuest(@RequestBody GuestModel guest) throws Exception {
        try {
            authService.registerGuest(guest);
            return "Successfully added guest";
        } catch (Exception e) {
            throw new Exception("Can't add guest", e);
        }
    }
}

