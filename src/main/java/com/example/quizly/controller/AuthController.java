package com.example.quizly.controller;

import com.example.quizly.models.request.authentication.GuestModel;
import com.example.quizly.models.request.authentication.LoginModel;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.models.response.Authentication.LoginResponse;
import com.example.quizly.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterModel user){
       try {
           if (user.getPassword().equals(user.getSecondPassword())) {
               return new ResponseEntity<String>(authService.register(user), HttpStatus.OK);
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return null;
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

