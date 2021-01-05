package com.example.quizly.accesssingdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guestId;

    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Guest(Long guestId, String name, Quiz quiz) {
        this.guestId = guestId;
        this.name = name;
        this.quiz = quiz;
    }

    public Guest() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

}
