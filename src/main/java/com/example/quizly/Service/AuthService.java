package com.example.quizly.Service;

import com.example.quizly.Models.request.Authentication.GuestModel;
import com.example.quizly.Models.request.Authentication.LoginModel;
import com.example.quizly.Models.request.Authentication.RegisterModel;
import com.example.quizly.Models.response.Authentication.LoginResponse;
import com.example.quizly.accessingData.Guest;
import com.example.quizly.accessingData.GuestRepository;
import com.example.quizly.accessingData.User;
import com.example.quizly.accessingData.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceInt{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public String register(RegisterModel user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            return "SuccesFull";
    }

    @Override
    public LoginResponse login(LoginModel user) {
        List<User> users = userRepository.findAll();
        LoginResponse returnValue = new LoginResponse();
        for (User user1 : users) {
            if(user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword())){
                returnValue.setUserId(user1.getUserId());
                returnValue.setName(user1.getName());
                return returnValue;
            }
        }
        return null;
    }

    public String registerGuest(GuestModel guest) {
        Guest newGuest = new Guest();
        newGuest.setName(guest.getName());
        newGuest.setCode(guest.getCode());
        guestRepository.save(newGuest);
        return "SuccesFull";
    }
}
