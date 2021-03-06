package com.example.quizly.mock;

import com.example.quizly.accesssingdata.User;
import com.example.quizly.accesssingdata.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepo implements UserRepository {
    private List<User> users = new ArrayList<>();
    private List<User> encryptedUsers = new ArrayList<>();
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public MockUserRepo(){
        users.add(new User(1L, "joseph joestar", "jojo@gmail.com", "iLoveEmiliaBRUH", new ArrayList<>()));
        users.add(new User(2L, "dio giovanna", "diogio@gmail.com", "annihilate69JOJO", new ArrayList<>()));
        users.add(new User(3L, "asta bruh", "futureWizardKinguh@gmail.com", "WillBenextWizardoOhkokhu", new ArrayList<>()));
        encryptedUsers.add(new User(3L, "asta bruh", "futureWizardKinguh@gmail.com", encoder.encode("WillBenextWizardoOhkokhu"), new ArrayList<>()));
    }



    @Override
    public User findByEmail(String email) {
        for (User user:encryptedUsers) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return users.stream().filter(user -> user.getUserId().equals(aLong)).findAny();
    }

    @Override
    public void deleteById(Long aLong) {
        users.removeIf(user -> user.getUserId().equals(aLong));
    }

    @Override
    public <S extends User> S save(S s) {
        users.add(s);
        return s;
    }



    //unnecessary methods....

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
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
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }
}
