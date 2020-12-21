package com.example.quizly.service;

import com.example.quizly.accesssingdata.User;
import com.example.quizly.accesssingdata.UserRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(User::new);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
