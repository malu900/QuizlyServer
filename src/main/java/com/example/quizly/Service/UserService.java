package com.example.quizly.Service;

import com.example.quizly.accessingData.Quiz;
import com.example.quizly.accessingData.QuizRepository;
import com.example.quizly.accessingData.User;
import com.example.quizly.accessingData.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
