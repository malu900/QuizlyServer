package com.example.quizly.Service;

import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        newQuestion.setAnswers(question.getAnswers());
        newQuestion.setQuestion(question.getQuestion());
        newQuestion = questionRepository.save(newQuestion);

        return "Succesvol";
    }

    public void DeleteQuestion(long questionId) {
        questionRepository.deleteById(questionId);
    }
}
