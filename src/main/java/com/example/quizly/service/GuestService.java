package com.example.quizly.service;

import com.example.quizly.accesssingdata.Guest;
import com.example.quizly.accesssingdata.GuestRepository;
import com.example.quizly.accesssingdata.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    @Autowired
    GuestRepository guestRepository;

    public Guest CreateGuest(Quiz quiz, String name){
        Guest guest = new Guest();
        guest.setName(name);
        guest.setQuiz(quiz);
        guest.setQuiz(quiz);
        guestRepository.save(guest);
        return guest;
    }
}
