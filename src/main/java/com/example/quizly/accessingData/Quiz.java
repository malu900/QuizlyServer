package com.example.quizly.accessingData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz {
    @Id
    private long quizId;

    @OneToMany
    private List<Question> questions;

    private String quizName;
}
