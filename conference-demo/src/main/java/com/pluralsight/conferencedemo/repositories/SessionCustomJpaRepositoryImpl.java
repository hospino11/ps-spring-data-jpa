package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionCustomJpaRepositoryImpl implements SessionCustomJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${DB_HOST}")
    String dbHost;

    @Override
    public List<Session> customGetSessions() {
        System.out.println("DB_HOST: " + dbHost);
        return entityManager.createQuery("SELECT s FROM Session s", Session.class)
                .getResultList();
    }
}
