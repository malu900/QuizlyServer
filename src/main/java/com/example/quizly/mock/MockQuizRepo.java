package com.example.quizly.mock;

import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.accesssingdata.QuizRepository;
import com.example.quizly.accesssingdata.User;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockQuizRepo implements QuizRepository {
    private List<Quiz> quizzes = new ArrayList<>();

    public MockQuizRepo(){
        quizzes.add(new Quiz(1L, "the weeb quiz", new ArrayList<>(), new User(), "1"));
        quizzes.add(new Quiz(2L, "potato race quiz", new ArrayList<>(), new User(),"1"));
        quizzes.add(new Quiz(3L, "memes on a stick", new ArrayList<>(), new User(),"1"));

    }

    @Override
    public List<Quiz> findAll() {
        return quizzes;
    }

    @Override
    public <S extends Quiz> S save(S s) {
        quizzes.add(s);
        return s;
    }

    @Override
    public Optional<Quiz> findById(Long aLong) {
        return quizzes.stream().filter(quiz -> quiz.getQuizId() == aLong).findAny();
    }

    @Override
    public void deleteById(Long aLong) {
        quizzes.removeIf(quiz -> quiz.getQuizId() == aLong);
    }

    //unnecessary methods...

    @Override
    public List<Quiz> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Quiz> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Quiz> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Quiz quiz) {

    }

    @Override
    public void deleteAll(Iterable<? extends Quiz> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Quiz> List<S> saveAll(Iterable<S> iterable) {
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
    public <S extends Quiz> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Quiz> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Quiz getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Quiz> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Quiz> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Quiz> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Quiz> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Quiz> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Quiz> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Quiz findByCode(String code) {
        return null;
    }
}
