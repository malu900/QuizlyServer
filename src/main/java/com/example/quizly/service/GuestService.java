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

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest CreateGuest(Quiz quiz, String name){
        Guest guest = new Guest();
        guest.setName(name);
        guest.setQuiz(quiz);
        guestRepository.save(guest);
        return guest;
    }

    public Guest CreateGuest(long guestId, Quiz quiz, String name){
        Guest guest = new Guest();
        guest.setGuestId(guestId);
        guest.setName(name);
        guest.setQuiz(quiz);
        guestRepository.save(guest);
        return guest;
    }

//    public boolean checkForDuplicateNames(String name){
//        for (Guest guest : guestRepository.findAll()) {
//            if(guest.getName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Guest findByName(String name){
//        for (Guest g : guestRepository.findAll()) {
//            if (g.getName().equals(name)) {
//                return g;
//            }
//        }
//        return new Guest();
//    }
}
