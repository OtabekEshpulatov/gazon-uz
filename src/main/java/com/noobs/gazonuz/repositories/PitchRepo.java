package com.noobs.gazonuz.repositories;

import com.noobs.gazonuz.domains.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PitchRepo extends JpaRepository<Pitch, String> {
    Optional<Pitch> findByLatitudeAndLongitude(String latitude , String longitude);

}
