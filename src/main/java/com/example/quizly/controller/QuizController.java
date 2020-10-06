package com.example.quizly.controller;

import com.example.quizly.Service.QuizService;
import com.example.quizly.accessingData.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

@RequestMapping("Beverages")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Quiz>> GetAllQuiz() {
        List<Quiz> quizzes =quizService.GetAllQuiz();
        if (quizzes== null)
        {
            try {
                throw new Exception("No beverages found : " + quizzes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody
    String addNewQuiz (@RequestBody Quiz quiz)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            quizService.AddQuiz(quiz);
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find beverage to delete", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String >UpdateQuiz(@RequestBody Quiz quiz) {
        String updateQuiz= quizService.UpdateQuiz(quiz);
        if (updateQuiz== null)
        {
            try {
                throw new Exception("No beverages found to update: " + updateQuiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateQuiz, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{QuizId}")
    public void DeleteQuiz(@PathVariable long QuizId)throws Exception{
        try {
            quizService.DeleteQuiz(QuizId);
        } catch (Exception e) {
            throw new Exception("Cant find beverage to delete", e);
        }
    }

}
