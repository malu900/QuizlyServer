package com.example.quizly.controller;

import com.example.quizly.Service.AnswerService;
import com.example.quizly.Service.QuestionService;
import com.example.quizly.Service.QuizService;
import com.example.quizly.accessingData.Answer;
import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final AnswerService answerService;

    @Autowired
    public QuestionController(QuestionService questionService, QuizService quizService, AnswerService answerService) {
        this.questionService = questionService;
        this.quizService = quizService;
        this.answerService = answerService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Question>> GetAllQuestions() {
        List<Question> questions =questionService.GetAllQuestions();
        if (questions== null)
        {
            try {
                throw new Exception("No questions found : " + questions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping(path="/{id}") // Map ONLY POST Requests
    public @ResponseBody
    String addNewQuestion (@RequestBody Question question, @PathVariable long id)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            Quiz quiz = quizService.findById(id);
            question.setQuiz(quiz);
            questionService.AddQuestion(question, quiz);
            if(question.getAnswers() != null && question.getAnswers().size()>1){
                for (Answer answer:question.getAnswers()) {
                    answerService.AddAnswer(answer);
                }
            }
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Can't add question", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String >UpdateQuiz(@RequestBody Question question) {
        String updateQuestion= questionService.UpdateQuestion(question);
        if (updateQuestion== null)
        {
            try {
                throw new Exception("No question found to update: " + updateQuestion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{QuestionId}")
    public void DeleteQuiz(@PathVariable long QuestionId)throws Exception{
        try {
            questionService.DeleteQuestion(QuestionId);
        } catch (Exception e) {
            throw new Exception("Cant find question to delete", e);
        }
    }
    
    @GetMapping("/{QuizId}/{RoundNumber}")
    public ResponseEntity<Question>getCurrentQuestion(@PathVariable long QuizId, @PathVariable int RoundNumber) throws Exception {
        try{
            Question question = questionService.getCurrentQuestion(QuizId, RoundNumber);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Can't find question", e);
        }
    }
}
