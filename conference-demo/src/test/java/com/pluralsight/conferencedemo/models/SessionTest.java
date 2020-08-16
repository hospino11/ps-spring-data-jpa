package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionJpaRepository jpaRepository;

    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.getSessionsThatHaveName("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionLengthNot() {
        List<Session> sessions = jpaRepository.findBySessionLengthNot(5);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionNameNotLike() {
        List<Session> sessions = jpaRepository.findBySessionNameNotLike("Java%");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionNameStartingWith() {
        List<Session> sessions = jpaRepository.findBySessionNameStartingWith("U");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionNameEndingWith() {
        List<Session> sessions = jpaRepository.findBySessionNameEndingWith("n");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionNameIgnoreCaseStartingWith() {
        List<Session> sessions = jpaRepository.findBySessionNameIgnoreCaseStartingWith("u");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldFindBySessionLengthLessThan() {
        List<Session> sessions = jpaRepository.findBySessionLengthLessThan(45);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void shouldGetSessionsByStartingSessionName() {
        Page<Session> sessions = jpaRepository.getSessionsByStartingSessionName("S", PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "sessionLength")));
        assertTrue(sessions.getTotalElements() > 0);
    }

    @Test
    public void shouldCustomGetSessions() {
        List<Session> sessions = jpaRepository.customGetSessions();
        assertTrue(sessions.size() > 0);
    }
}
