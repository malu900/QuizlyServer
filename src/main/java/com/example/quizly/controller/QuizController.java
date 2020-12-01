package com.example.quizly.controller;

import com.example.quizly.Service.AuthService;
import com.example.quizly.Service.QuestionService;
import com.example.quizly.Service.QuizService;
import com.example.quizly.Service.UserService;
import com.example.quizly.accessingData.Question;
import com.example.quizly.accessingData.Quiz;
import com.example.quizly.accessingData.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
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

    @MessageMapping("/getAll")
    @SendTo("/topic/quizes")
    public ResponseEntity<List<Quiz>> GetAllQuiz() {
        List<Quiz> quizzes =quizService.GetAllQuiz();
        if (quizzes== null)
        {
            try {
                throw new Exception("No quiz found : " + quizzes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping(path="/{userId}") // Map ONLY POST Requests
    public @ResponseBody
    String addNewQuiz (@RequestBody Quiz quiz, @PathVariable long userId)throws Exception{
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            User user = userService.findById(userId);
            quiz.setUser(user);
            quizService.AddQuiz(quiz, user);
            if(quiz.getQuestions()!= null && quiz.getQuestions().size() < 1){
                for (Question question: quiz.getQuestions()) {
                    questionService.AddQuestion(question,null);
                }
            }
            return "Saved";
        } catch (Exception e) {
            throw new Exception("Cant find quiz to delete", e);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String >UpdateQuiz(@RequestBody Quiz quiz) {
        String updateQuiz= quizService.UpdateQuiz(quiz);
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
    public void DeleteQuiz(@PathVariable long QuizId)throws Exception{
        try {
            quizService.DeleteQuiz(QuizId);
        } catch (Exception e) {
            throw new Exception("Cant find quiz to delete", e);
        }
    }

}
