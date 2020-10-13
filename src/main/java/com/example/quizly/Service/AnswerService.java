package com.example.quizly.Service;

import com.example.quizly.accessingData.Answer;
import com.example.quizly.accessingData.AnswerRepository;
import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class AnswerService {
    private final AnswerRepository answerRepository;


    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> GetAllAnswers(){
        return answerRepository.findAll();
    }

    public String AddAnswer(Answer answer) {
        answerRepository.save(answer);
        return "Succesvol";
    }

    public String UpdateAnswer(Answer answer) {
        Optional<Answer> answerFromDb = answerRepository.findById(answer.getAnswerId());
        Answer newAnswer = answerFromDb.get();
        newAnswer.setAnswer(answer.getAnswer());

        return "Succesvol";
    }

    public void DeleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

}
