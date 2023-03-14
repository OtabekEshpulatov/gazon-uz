package com.noobs.gazonuz.domains;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PitchRepository extends JpaRepository<Pitch, String> {
    Optional<List<Pitch>> findByLongitudeAndLatitude(String longitude , String latitude);

    List<Pitch> findAll();


}