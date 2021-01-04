package com.example.quizly.service;

import com.example.quizly.models.request.authentication.GuestModel;
import com.example.quizly.models.request.authentication.LoginModel;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.models.response.Authentication.LoginResponse;
import com.example.quizly.accesssingdata.Guest;
import com.example.quizly.accesssingdata.GuestRepository;
import com.example.quizly.accesssingdata.User;
import com.example.quizly.accesssingdata.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInt{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuestRepository guestRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String register(RegisterModel user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(newUser);
            return "SuccesFull";
    }

    @Override
    public LoginResponse login(LoginModel user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        LoginResponse returnValue = new LoginResponse();
        if(user1.getEmail().equals(user.getEmail()) && BCrypt.checkpw(user.getPassword(), user1.getPassword())){
            returnValue.setUserId(user1.getUserId());
            returnValue.setName(user1.getName());
            return returnValue;
        }
        return null;
    }

    @Override
    public String registerGuest(GuestModel guest) {
        Guest newGuest = new Guest();
        newGuest.setName(guest.getName());
        guestRepository.save(newGuest);
        return "SuccesFull";
    }
}
