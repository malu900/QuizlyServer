package com.example.quizly.service;

import com.example.quizly.accesssingdata.*;
import com.example.quizly.logic.CodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final GuestRepository guestRepository;
    private final GuestService guestService;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository, GuestRepository guestRepository, GuestService guestService) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.guestRepository = guestRepository;
        this.guestService = guestService;
    }

    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    public String addQuiz(Quiz quiz, User user) {
        quiz.setCode(CodeGenerator.generateRandomCode());
        quizRepository.save(quiz);
        user.getQuizzes().add(quiz);
        userRepository.save(user);
        return "Succesvol";
    }

    public String updateQuiz(Quiz quiz) {
        Optional<Quiz> quizFromDb = quizRepository.findById(quiz.getQuizId());
        if(quizFromDb.isPresent()){
            Quiz newQuiz = quizFromDb.get();

            newQuiz.setQuizName(quiz.getQuizName());
            newQuiz.setQuestions(quiz.getQuestions());
            quizRepository.save(newQuiz);
            return "Succesvol";
        }else{
            return "Failed in updateQuiz(): quizFromDb is not Present";
        }
    }

    public void deleteQuiz(long quizId) {
        quizRepository.deleteById(quizId);
    }

    public Quiz findById(long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElseGet(Quiz::new);
    }

    @Transactional
    public List<Guest> JoinQuiz(String name, String code) throws Exception {
        Quiz retrievedQuiz = quizRepository.findByCode(code);
        if(retrievedQuiz != null){
            Guest guest = guestService.CreateGuest(retrievedQuiz, name);
            if(guest != null){
                retrievedQuiz.getParticipants().add(guest);
                quizRepository.save(retrievedQuiz);
                return retrievedQuiz.getParticipants();
            }
            else {
                throw new NullPointerException();
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    @Transactional
    public List<Guest> LeaveQuiz(String code, String name) {
        Quiz retrievedQuiz = quizRepository.findByCode(code);
        if(retrievedQuiz != null) {
            Guest guest = guestRepository.findByName(name);
            guestRepository.delete(guest);
            retrievedQuiz.getParticipants().remove(guest);
            quizRepository.save(retrievedQuiz);
            return retrievedQuiz.getParticipants();
        }
        else{
            return null;
        }
    }

    public List<Quiz> getQuizzesByUserID(Long userID) {
        return quizRepository.getQuizByUserID(userID);
    }

    public List<User> JoinQuizAsHost(User user, String code) {
        return null;
    }
}
