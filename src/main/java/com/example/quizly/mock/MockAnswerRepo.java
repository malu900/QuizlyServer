package com.example.quizly.mock;

import com.example.quizly.accesssingdata.Answer;
import com.example.quizly.accesssingdata.AnswerRepository;
import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.Quiz;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockAnswerRepo implements AnswerRepository {
    private List<Answer> answers = new ArrayList<>();

    public MockAnswerRepo(){
        answers.add(new Answer(1L, "pallet town", true, new Question()));
        answers.add(new Answer(1L, "to stop making people do drugs", false, new Question()));
        answers.add(new Answer(1L, "lazy town", false, new Question()));
    }

    @Override
    public List<Answer> findAll() {
        return answers;
    }

    @Override
    public Optional<Answer> findById(Long aLong) {
        return answers.stream().filter(answer -> answer.getAnswerId().equals(aLong)).findAny();
    }

    @Override
    public <S extends Answer> S save(S s) {
        answers.add(s);
        return s;
    }

    @Override
    public void deleteById(Long aLong) {
        answers.removeIf(answer -> answer.getAnswerId().equals(aLong));
    }




    //unnecessary methods...

    @Override
    public List<Answer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Answer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Answer> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Answer answer) {

    }

    @Override
    public void deleteAll(Iterable<? extends Answer> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Answer> List<S> saveAll(Iterable<S> iterable) {
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
    public <S extends Answer> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Answer> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Answer getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Answer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Answer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Answer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Answer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Answer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Answer> boolean exists(Example<S> example) {
        return false;
    }
}
