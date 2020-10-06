package com.example.quizly.Service;

import com.example.quizly.accessingData.Quiz;
import com.example.quizly.accessingData.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;


    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> GetAllQuiz(){
        return quizRepository.findAll();
    }

    public String AddQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return "Succesvol";
    }

    public String UpdateQuiz(Quiz quiz) {
        Optional<Quiz> quizFromDb = quizRepository.findById(quiz.getQuizId());
        Quiz newQuiz = quizFromDb.get();
        newQuiz.setQuizName(quiz.getQuizName());
        newQuiz.setQuestions(quiz.getQuestions());
        newQuiz = quizRepository.save(newQuiz);
        return "Succesvol";
    }

    public void DeleteQuiz(long quizId) {
        quizRepository.deleteById(quizId);
    }
}
