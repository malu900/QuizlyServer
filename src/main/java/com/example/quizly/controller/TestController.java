package com.example.quizly.controller;

import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.service.QuestionService;
import com.example.quizly.service.QuizService;
import com.example.quizly.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    private final QuizService quizService;
    private final UserService userService;
    private final QuestionService questionService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TestController(QuizService quizService, UserService userService, QuestionService questionService) {
        this.quizService = quizService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping("/getAllQuizes")
    public ResponseEntity<?> getAllQuizes() throws JsonProcessingException {
        try{
            List<Quiz> quizzes = quizService.getAllQuiz();
            String json = objectMapper.writeValueAsString(quizzes);
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Can't find all quizzes");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
    }
}
