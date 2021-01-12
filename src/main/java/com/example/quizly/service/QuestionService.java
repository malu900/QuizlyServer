package com.example.quizly.service;

import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.QuestionRepository;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.mock.MockQuestionRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizService quizService;

    public QuestionService(QuestionRepository questionRepository, QuizService quizService) {
        this.questionRepository = questionRepository;
        this.quizService = quizService;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public String addQuestion(Question question, Quiz quiz) {
        questionRepository.save(question);
        if(quiz!= null){
            quiz.getQuestions().add(question);
        }
        return "Saved";
    }

    public String updateQuestion(Question question) {
        Optional<Question> questionFromDb = questionRepository.findById(question.getQuestionId());
        if(questionFromDb.isPresent()){
            Question newQuestion = questionFromDb.get();

            newQuestion.setAnswers(question.getAnswers());
            newQuestion.setQuestionName(question.getQuestionName());
            newQuestion = questionRepository.save(newQuestion);
            newQuestion.setQuestionName(question.getQuestionName());
            newQuestion.setAnswers(question.getAnswers());

            return "Succesvol";
        }
        return "Failed in updateQuestion(): questionFromDb is not Present";
    }

    public void deleteQuestion(long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Question getCurrentQuestion(long quizId, int roundNumber){
        Quiz quiz = quizService.findById(quizId);
        System.out.println(quiz);
        return quiz.getQuestions().get(roundNumber-1);
    }

    public Question findById(long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElseGet(Question::new);
    }
}
