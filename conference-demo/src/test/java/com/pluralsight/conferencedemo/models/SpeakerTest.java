package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeakerTest {
    @Autowired
    private SpeakerJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testFind() throws Exception {
        Speaker speaker = repository.getOne(1L);
        assertNotNull(speaker);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Speaker s = new Speaker();
        s.setCompany("Pluralsight");
        s.setFirstName("Dan");
        s.setLastName("Bunker");
        s.setTitle("Author");
        s.setSpeakerBio("Consulting and mentoring");
        s = repository.saveAndFlush(s);

        // clear the persistence context so we don't return the previously cached location object
        // this is a test only thing and normally doesn't need to be done in prod code
        entityManager.clear();

        Speaker otherSpeaker = repository.getOne(s.getSpeakerId());
        assertEquals("Dan", otherSpeaker.getFirstName());

        repository.deleteById(otherSpeaker.getSpeakerId());
    }

    @Test
    public void shouldFindByFirstNameAndLastName() {
        List<Speaker> speakers = repository.findByFirstNameAndLastName("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByFirstNameOrLastName() {
        List<Speaker> speakers = repository.findByFirstNameOrLastName("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByFirstNameLikeOrLastName() {
        List<Speaker> speakers = repository.findByFirstNameContainsAndLastName("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByFirstNameOrLastNameLike() {
        List<Speaker> speakers = repository.findByFirstNameOrLastNameContains("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByFirstNameLikeOrLastNameLike() {
        List<Speaker> speakers = repository.findByFirstNameLikeOrLastNameLike("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByFirstNameIgnoreCaseLikeOrLastNameLike() {
        List<Speaker> speakers = repository.findByFirstNameIgnoreCaseLikeOrLastNameLike("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindBySpeakerPhotoIsNull() {
        List<Speaker> speakers = repository.findBySpeakerPhotoIsNull();
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByCompanyIn() {
        List<String> companies = Arrays.asList("National Bank", "Contoso");
        List<Speaker> speakers = repository.findByCompanyIn(companies);
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByCompanyIgnoreCase() {
        List<Speaker> speakers = repository.findByCompanyIgnoreCase("national bank");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindByLastNameOrderByFirstNameAsc() {
        List<Speaker> speakers = repository.findByLastNameOrderByFirstNameAsc("Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void shouldFindFirstByLastNameIgnoreCase() {
        Speaker speaker = repository.findFirstByLastNameIgnoreCase("clark");
        assertNotNull(speaker);
        assertEquals("Clark", speaker.getLastName());
    }

    @Test
    public void shouldFindTop3ByFirstNameIgnoreCaseLike() {
        List<Speaker> speakers = repository.findTop3ByFirstNameIgnoreCaseContains("j");
        assertNotNull(speakers);
        assertTrue(speakers.size() > 0);
    }
}
