package com.example.quizly.Service;

import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.QuestionRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
import com.example.quizly.accessingData.Quiz;
import com.example.quizly.accessingData.QuizRepository;
>>>>>>> DevRens

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
@Service
=======
>>>>>>> DevRens
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
<<<<<<< HEAD
        newQuestion.setAnswers(question.getAnswers());
        newQuestion.setQuestion(question.getQuestion());
        newQuestion = questionRepository.save(newQuestion);
=======
        newQuestion.setQuestionName(question.getQuestionName());
        newQuestion.setAnswers(question.getAnswers());
>>>>>>> DevRens

        return "Succesvol";
    }

<<<<<<< HEAD
    public void DeleteQuestion(long questionId) {
=======
    public void DeleteQuiz(long questionId) {
>>>>>>> DevRens
        questionRepository.deleteById(questionId);
    }
}
