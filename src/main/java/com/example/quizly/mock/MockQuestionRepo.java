package com.example.quizly.mock;

import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.QuestionRepository;
import com.example.quizly.accesssingdata.Quiz;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockQuestionRepo implements QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    public MockQuestionRepo(){
        questions.add(new Question(1L, new ArrayList<>(), new Quiz(), "what is giorno giovanna's dream?"));
        questions.add(new Question(2L, new ArrayList<>(), new Quiz(), "how old is ash ketchum from pallet town?"));
        questions.add(new Question(3L, new ArrayList<>(), new Quiz(), "is that a jojo reference?"));
    }


    @Override
    public List<Question> findAll() {
        return questions;
    }

    @Override
    public <S extends Question> S save(S s) {
        questions.add(s);
        return s;
    }

    @Override
    public Optional<Question> findById(Long aLong) {
        return questions.stream().filter(question -> question.getQuestionId() == aLong).findAny();
    }

    @Override
    public void deleteById(Long aLong) {
        questions.removeIf(question -> question.getQuestionId() == aLong);
    }

    //unnecessary methods...

    @Override
    public List<Question> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Question> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Question question) {

    }

    @Override
    public void deleteAll(Iterable<? extends Question> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Question> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Question> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Question> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Question getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Question> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Question> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Question> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Question> boolean exists(Example<S> example) {
        return false;
    }
}
