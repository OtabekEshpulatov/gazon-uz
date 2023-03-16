package com.noobs.gazonuz.repositories.pitch;


import com.noobs.gazonuz.domains.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchRepository extends JpaRepository<Pitch, String> {
}