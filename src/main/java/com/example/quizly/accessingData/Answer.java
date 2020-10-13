package com.example.quizly.accessingData;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    private Long answerId;

    private String answer;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
