package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TimeSlotJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TimeSlotTest {
    @Autowired
    private TimeSlotJpaRepository jpaRepository;

    @Test
    public void shouldFindByStartTimeBefore() {
        LocalTime startTime = LocalTime.of(10, 0, 0);
        List<TimeSlot> timeSlots = jpaRepository.findByStartTimeBefore(startTime);

        assertTrue(timeSlots.size() > 0);
    }

    @Test
    public void shouldFindByStartTimeAfter() {
        LocalTime startTime = LocalTime.of(10, 0, 0);
        List<TimeSlot> timeSlots = jpaRepository.findByStartTimeAfter(startTime);

        assertTrue(timeSlots.size() > 0);
    }

    @Test
    public void shouldFindByStartTimeAfterEquals() {
        LocalTime startTime = LocalTime.of(10, 0, 0);
        List<TimeSlot> timeSlots = jpaRepository.findByStartTimeGreaterThanEqual(startTime);

        assertTrue(timeSlots.size() > 0);
    }

    @Test
    public void shouldFindByStartTimeBetween() {
        LocalTime initialTime = LocalTime.of(10, 0, 0);
        LocalTime finalTime = LocalTime.of(13, 0, 0);
        List<TimeSlot> timeSlots = jpaRepository.findByStartTimeBetween(initialTime, finalTime);

        assertTrue(timeSlots.size() > 0);
    }
}
