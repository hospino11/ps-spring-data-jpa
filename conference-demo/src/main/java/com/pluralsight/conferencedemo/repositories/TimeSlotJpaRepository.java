package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotJpaRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByStartTimeBefore(LocalTime startTime);
    List<TimeSlot> findByStartTimeAfter(LocalTime startTime);
    List<TimeSlot> findByStartTimeGreaterThanEqual(LocalTime startTime);
    List<TimeSlot> findByStartTimeBetween(LocalTime initialTime, LocalTime finalTime);
}
