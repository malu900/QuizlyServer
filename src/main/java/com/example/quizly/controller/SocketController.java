package com.example.quizly.controller;

import com.example.quizly.Service.QuizService;
import com.example.quizly.accessingData.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SocketController {
private final QuizService quizService;

    @Autowired
    public SocketController (QuizService quizService){
        this.quizService = quizService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/getAll")
    @SendTo("/topic/quizzes")
    public String GetAllQuiz() throws JsonProcessingException {
        List<Quiz> quizzes =quizService.GetAllQuiz();
        if (quizzes== null)
        {
            try {
                throw new Exception("No quiz found : " + quizzes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String json = objectMapper.writeValueAsString(quizzes);
        return json;
    }
}
