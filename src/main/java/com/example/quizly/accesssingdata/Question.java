package com.example.quizly.accesssingdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private List<Answer> answers;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String questionName;

    public Question(long questionId, List<Answer> answers,  Quiz quiz, String questionName){
        this.questionId = questionId;
        this.answers = answers;
        this.quiz = quiz;
        this.questionName = questionName;
    }

    public Question() {

    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
