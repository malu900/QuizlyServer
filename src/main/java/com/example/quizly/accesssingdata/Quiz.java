package com.example.quizly.accesssingdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    private String quizName;

    private String code;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    private List<Question> questions;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    private List<Guest> participants;


    public Quiz(long quizId, String quizName, List<Question> questions, User user, String code) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questions = questions;
        this.user = user;
        this.code = code;
    }

    public Quiz() { }

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
