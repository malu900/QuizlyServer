package com.example.quizly.controller;

import com.example.quizly.service.AnswerService;
import com.example.quizly.service.QuestionService;
import com.example.quizly.accesssingdata.Answer;
import com.example.quizly.accesssingdata.Question;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answer")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    @Autowired
    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers =answerService.getAllAnswers();
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

    @PostMapping(path="/{id}") // Map ONLY POST Requests
    public @ResponseBody
    String addNewAnswer (@RequestBody Answer answer, @PathVariable long id)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            Question question = questionService.findById(id);
            answer.setQuestion(question);
            answerService.addAnswer(answer, question);
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find answer to delete", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateAnswer(@RequestBody Answer answer) {
        String updateAnswer= answerService.updateAnswer(answer);
        if (updateAnswer== null)
        {
            try {
                throw new NotFoundException("No answer found to update: " + updateAnswer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }
    @DeleteMapping(path = "{answerId}")
    public void deleteAnswer(@PathVariable long answerId)throws NotFoundException{
        try {
            answerService.deleteAnswer(answerId);
        } catch (Exception e) {
            throw new NotFoundException("Cant find answer to delete", e);
        }
    }
}
