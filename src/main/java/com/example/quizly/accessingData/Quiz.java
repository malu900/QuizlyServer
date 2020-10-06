package com.example.quizly.accessingData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quizzes")
public class Quiz {
    @Id
    private long QuizId;
}
