package com.example.quizly.accessingData;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    private Long answerId;

    private String answer;
}
