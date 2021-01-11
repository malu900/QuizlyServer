package com.example.quizly.models.response.Authentication;

import com.example.quizly.accesssingdata.Guest;
import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.accesssingdata.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

public class QuizREST {
    private long quizId;
    private String quizName;
    private String code;
    private List<Question> questions;
    private User user;
    private List<Guest> participants;


    public QuizREST(Quiz quiz) {
        this.quizId = quiz.getQuizId();
        this.quizName = quiz.getQuizName();
        this.questions = quiz.getQuestions();
        this.user = quiz.getUser();
        this.code = quiz.getCode();
        this.participants = quiz.getParticipants();
    }

    public QuizREST() { }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){ this.questions.add(question); }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Guest> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Guest> participants) {
        this.participants = participants;
    }

}
