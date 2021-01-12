package com.example.quizly.mock;

import com.example.quizly.accesssingdata.Guest;
import com.example.quizly.accesssingdata.GuestRepository;
import org.apache.catalina.AccessLog;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockGuestRepo implements GuestRepository {
    private List<Guest> guests = new ArrayList<>();

    @Override
    public <S extends Guest> S save(S s) {
        guests.add(s);
        return s;
    }

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public Optional<Guest> findById(Long aLong) {
        return guests.stream().filter(guest -> guest.getGuestId().equals(aLong)).findFirst();
    }

    //unnecessary methods

    @Override
    public List<Guest> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Guest> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Guest> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Guest guest) {

    }

    @Override
    public void deleteAll(Iterable<? extends Guest> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Guest> List<S> saveAll(Iterable<S> iterable) {
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
    public <S extends Guest> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Guest> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Guest getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Guest> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Guest> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Guest> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Guest> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Guest> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Guest> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Guest findByName(String name) {
        return null;
    }
}
