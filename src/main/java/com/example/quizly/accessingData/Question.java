package com.example.quizly.accessingData;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    @OneToMany//(fetch = FetchType.EAGER)
    //@JoinColumn(name = "questionId", nullable = false)
    private List<Answer> answers;

    private String question;

}
