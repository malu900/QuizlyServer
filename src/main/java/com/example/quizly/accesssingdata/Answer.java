package com.example.quizly.accesssingdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.swing.text.AbstractDocument;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerContent;

    private boolean rightAnswer;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(long answerId, String answerContent, boolean rightAnswer, Question question){
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.rightAnswer = rightAnswer;
        this.question = question;
    }

    public Answer() {

    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answer) {
        this.answerContent = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
