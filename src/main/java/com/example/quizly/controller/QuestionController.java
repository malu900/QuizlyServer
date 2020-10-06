package com.example.quizly.controller;

import com.example.quizly.Service.QuestionService;
import com.example.quizly.accessingData.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("Questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Question>> GetQuestions() {
        List<Question> questions =questionService.GetAllQuestions();
        if (questions== null)
        {
            try {
                throw new Exception("No beverages found : " + questions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody
    String AddNewQuestion (@RequestBody Question question)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            questionService.AddQuestion(question);
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find beverage to delete", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String >UpdateQuestion(@RequestBody Question question) {
        String updateBeverage= questionService.UpdateQuestion(question);
        if (updateBeverage== null)
        {
            try {
                throw new Exception("No beverages found to update: " + updateBeverage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateBeverage, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{QuestionId}")
    public void DeleteBeverage(@PathVariable long QuestionId)throws Exception{
        try {
            questionService.DeleteQuestion(QuestionId);
        } catch (Exception e) {
            throw new Exception("Cant find beverage to delete", e);
        }
    }
}
