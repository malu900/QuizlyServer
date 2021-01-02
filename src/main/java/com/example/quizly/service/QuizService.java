package com.example.quizly.service;

import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.accesssingdata.QuizRepository;
import com.example.quizly.accesssingdata.User;
import com.example.quizly.accesssingdata.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    public String addQuiz(Quiz quiz, User user) {
        quizRepository.save(quiz);
        user.getQuizzes().add(quiz);
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

    public void addQuiz(Quiz quiz) {
    }

    @Transactional
    public List<User> JoinQuiz(long id, long userId) {
        Quiz retrievedQuiz = findById(id);
        User user = userRepository.findById(userId).get();
        List<Quiz> quizzes = user.getQuizzes();
        quizzes.add(retrievedQuiz);
        user.setQuizzes(quizzes);
        userRepository.save(user);
        retrievedQuiz.getParticipants().add(user);
        quizRepository.save(retrievedQuiz);
        return retrievedQuiz.getParticipants();
    }

    @Transactional
    public List<User> LeaveQuiz(long id, long userId) {
        Quiz retrievedQuiz = findById(id);
        User user = userRepository.findById(userId).get();
        List<Quiz> quizzes = user.getQuizzes();
        quizzes.remove(retrievedQuiz);
        user.setQuizzes(quizzes);
        userRepository.save(user);
        retrievedQuiz.getParticipants().remove(user);
        quizRepository.save(retrievedQuiz);
        return retrievedQuiz.getParticipants();
    }
}
