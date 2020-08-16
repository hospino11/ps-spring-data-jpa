package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {

    List<Speaker> findByFirstNameAndLastName(String firstName, String lastName);

    List<Speaker> findByFirstNameOrLastName(String firstName, String lastName);

    List<Speaker> findByFirstNameContainsAndLastName(String firstName, String lastName);

    List<Speaker> findByFirstNameOrLastNameContains(String firstName, String lastName);

    List<Speaker> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

    List<Speaker> findByFirstNameIgnoreCaseLikeOrLastNameLike(String firstName, String lastName);

    List<Speaker> findBySpeakerPhotoIsNull();

    List<Speaker> findByCompanyIn(List<String> companies);

    List<Speaker> findByCompanyIgnoreCase(String company);

    List<Speaker> findByLastNameOrderByFirstNameAsc(String lastName);

    Speaker findFirstByLastNameIgnoreCase(String lastName);

    List<Speaker> findTop3ByFirstNameIgnoreCaseContains(String firstName);
}
