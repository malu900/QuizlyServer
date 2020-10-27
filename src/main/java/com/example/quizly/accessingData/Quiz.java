package com.example.quizly.accessingData;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz {
    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

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
    @JsonManagedReference("questions")
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    private String quizName;

    @JsonBackReference("user")
    @ManyToOne
    private User user;

}
