package com.example.quizly.controller;

import com.example.quizly.accesssingdata.Guest;
import com.example.quizly.accesssingdata.User;
import com.example.quizly.models.WsMethod;
import com.example.quizly.models.WsResponse;
import com.example.quizly.service.QuizService;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SocketController {
private final QuizService quizService;

    @Autowired
    public SocketController (QuizService quizService, UserService userService){
        this.quizService = quizService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/getAll")
    @SendTo("/topic/quizzes")
    public String getAllQuiz() throws JsonProcessingException {
        List<Quiz> quizzes =quizService.getAllQuiz();
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

    @MessageMapping("/leave/{id}")
    @SendTo("/topic/quizzes/{id}")
    public ResponseEntity<WsResponse> leaveGame(@Payload long userId, @DestinationVariable long id) throws JsonProcessingException {
        try{
            List<Guest>users = quizService.LeaveQuiz(id, userId);
            String json = objectMapper.writeValueAsString(users);
            WsResponse response = new WsResponse(json, WsMethod.LEAVE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Couldn't leave the quiz");
            WsResponse response = new WsResponse(json, WsMethod.LEAVE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/join/{id}")
    @SendTo("/topic/quizzes/{id}")
    public ResponseEntity<WsResponse> joinGame(@Payload long guestId, @Payload String code, @DestinationVariable long id) throws JsonProcessingException {
        try {
            List<Guest>users = quizService.JoinQuiz(id, guestId, code);
            String json = objectMapper.writeValueAsString(users);
            WsResponse response = new WsResponse(json, WsMethod.JOIN);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Couldn't join the quiz");
            WsResponse response = new WsResponse(json, WsMethod.JOIN);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



}
