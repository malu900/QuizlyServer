package com.example.quizly.controller;

import com.example.quizly.accesssingdata.*;
import com.example.quizly.models.WsMethod;
import com.example.quizly.models.WsResponse;
import com.example.quizly.service.QuizService;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SocketController {
private final QuizService quizService;

    @Autowired
    public SocketController(QuizService quizService){
        this.quizService = quizService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/getAll")
    @SendTo("/topic/quizzes")
    public ResponseEntity<WsResponse> getAllQuiz() throws JsonProcessingException {
        try{
            WsResponse response;
            List<Quiz> quizzes = quizService.getAllQuiz();
            String json = objectMapper.writeValueAsString(quizzes);
            response = new WsResponse(json, WsMethod.GETALL);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Can't find all quizzes");
            WsResponse response = new WsResponse(json, WsMethod.GETALL);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/leave/{code}")
    @SendTo("/topic/quizzes/{code}")
    public ResponseEntity<WsResponse> leaveGame(@DestinationVariable String code, @Payload String name) throws JsonProcessingException {
        try{
            List<Guest>users = quizService.LeaveQuiz(code, name);
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

    @MessageMapping("/join/{code}")
    @SendTo("/topic/quizzes/{code}")
    public ResponseEntity<WsResponse> joinGame(@DestinationVariable String code, @Payload String name) throws JsonProcessingException {
        try {
            List<Guest>guests = quizService.JoinQuiz(name, code);
            String json = objectMapper.writeValueAsString(guests);
            WsResponse response = new WsResponse(json, WsMethod.JOIN);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Couldn't join the quiz");
            WsResponse response = new WsResponse(json, WsMethod.JOIN);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @MessageMapping("/startGame/{code}")
    @SendTo("/topic/startGame/{code}")
    public ResponseEntity<WsResponse> startGame(@Payload Boolean start) throws JsonProcessingException {
        try {
            Boolean Start = start;
            String json = objectMapper.writeValueAsString(Start);
            WsResponse response = new WsResponse(json, WsMethod.START);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            Boolean Start = start;
            String json = objectMapper.writeValueAsString("Couldn't start the quiz");
            WsResponse response = new WsResponse(json, WsMethod.START);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/join/host/{code}")
    @SendTo("/topic/quizzes/{code}")
    public ResponseEntity<WsResponse> joinGame(@DestinationVariable String code, @Payload User user) throws JsonProcessingException {
        try {
            List<User>users = quizService.JoinQuizAsHost(user, code);
            String json = objectMapper.writeValueAsString(users);
            WsResponse response = new WsResponse(json, WsMethod.JOINASHOST);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String json = objectMapper.writeValueAsString("Couldn't join the quiz");
            WsResponse response = new WsResponse(json, WsMethod.JOINASHOST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



}
