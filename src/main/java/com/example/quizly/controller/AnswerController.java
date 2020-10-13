package com.example.quizly.controller;

import com.example.quizly.Service.AnswerService;
import com.example.quizly.Service.QuestionService;
import com.example.quizly.accessingData.Answer;
import com.example.quizly.accessingData.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Answer>> GetAllAnswers() {
        List<Answer> answers =answerService.GetAllAnswers();
        if (answers== null)
        {
            try {
                throw new Exception("No answers found : " + answers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody
    String addNewAnswer (@RequestBody Answer answer)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            answerService.AddAnswer(answer);
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find answer to delete", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String >UpdateAnswer(@RequestBody Answer answer) {
        String updateAnswer= answerService.UpdateAnswer(answer);
        if (updateAnswer== null)
        {
            try {
                throw new Exception("No answer found to update: " + updateAnswer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }
    @DeleteMapping(path = "{answerId}")
    public void DeleteAnswer(@PathVariable long answerId)throws Exception{
        try {
            answerService.DeleteAnswer(answerId);
        } catch (Exception e) {
            throw new Exception("Cant find answer to delete", e);
        }
    }
}
