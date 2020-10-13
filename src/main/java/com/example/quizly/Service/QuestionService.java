package com.example.quizly.Service;

import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.QuestionRepository;
import com.example.quizly.accessingData.Quiz;
import com.example.quizly.accessingData.QuizRepository;

import java.util.List;
import java.util.Optional;

public class QuestionService {
    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> GetAllQuestions(){
        return questionRepository.findAll();
    }

    public String AddQuestion(Question question) {
        questionRepository.save(question);
        return "Succesvol";
    }

    public String UpdateQuestion(Question question) {
        Optional<Question> questionFromDb = questionRepository.findById(question.getQuestionId());
        Question newQuestion = questionFromDb.get();
        newQuestion.setQuestionName(question.getQuestionName());
        newQuestion.setAnswers(question.getAnswers());

        return "Succesvol";
    }

    public void DeleteQuiz(long questionId) {
        questionRepository.deleteById(questionId);
    }
}
