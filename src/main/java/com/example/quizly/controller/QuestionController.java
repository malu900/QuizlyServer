package com.example.quizly.controller;

import com.example.quizly.Service.QuestionService;
import com.example.quizly.Service.QuizService;
import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody
    String addNewQuestion (@RequestBody Question question)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            questionService.AddQuestion(question);
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find question to delete", e);
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
}