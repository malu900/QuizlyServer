package com.example.quizly.controller;

import com.example.quizly.models.response.Authentication.QuizREST;
import com.example.quizly.service.QuestionService;
import com.example.quizly.service.QuizService;
import com.example.quizly.service.UserService;
import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.accesssingdata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;
    private final QuestionService questionService;

    @Autowired
    public QuizController(QuizService quizService, UserService userService, QuestionService questionService) {
        this.quizService = quizService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping(path="/{quizId}")
    public ResponseEntity<Quiz> getById(@PathVariable Long quizId) {
       Quiz quiz = quizService.findById(quizId);
       return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping(path="/{userId}") // Map ONLY POST Requests
    public @ResponseBody
    String addNewQuiz (@RequestBody Quiz quiz, @PathVariable long userId)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            User user = userService.findById(userId);
            quiz.setUser(user);
            quizService.addQuiz(quiz, user);
            if(quiz.getQuestions()!= null && quiz.getQuestions().size() < 1){
                for (Question question: quiz.getQuestions()) {
                    questionService.addQuestion(question,null);
                }
            }
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find quiz to delete", e);
        }
    }
    @GetMapping(path = "/GetByUserID/{userID}")
    public List<QuizREST> getPersonalQuizzes(@PathVariable Long userID){
        List<QuizREST> returnlist = new ArrayList<>();
        List<Quiz> quizzes = quizService.getQuizzesByUserID(userID);
        for (Quiz quiz: quizzes)
        {
            returnlist.add(new QuizREST(quiz));
        }
        return returnlist;
    }
    @PutMapping(path = "/update")
    public ResponseEntity<String > updateQuiz(@RequestBody Quiz quiz) {
        String updateQuiz= quizService.updateQuiz(quiz);
        if (updateQuiz== null)
        {
            try {
                throw new Exception("No quiz found to update: " + updateQuiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(updateQuiz, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{QuizId}")
    public void deleteQuiz(@PathVariable long QuizId)throws Exception{
        try {
            quizService.deleteQuiz(QuizId);
        } catch (Exception e) {
            throw new Exception("Cant find quiz to delete", e);
        }
    }

}
