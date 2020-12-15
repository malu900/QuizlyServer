package com.example.quizly.service;

import com.example.quizly.accesssingdata.Answer;
import com.example.quizly.accesssingdata.AnswerRepository;
import com.example.quizly.accesssingdata.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;


    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }

    public String addAnswer(Answer answer, Question question) {
        answerRepository.save(answer);
        if(question!= null){
            question.getAnswers().add(answer);
        }
        return "Succesvol";
    }

    public String updateAnswer(Answer answer) {
        Optional<Answer> answerFromDb = answerRepository.findById(answer.getAnswerId());
        if(answerFromDb.isPresent()){
            Answer newAnswer = answerFromDb.get();

            newAnswer.setAnswerContent(answer.getAnswerContent());

            return "Succesvol";
        }
        else{
            return "Failed: answerFromDb is not Present!";
        }
    }

    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

}
