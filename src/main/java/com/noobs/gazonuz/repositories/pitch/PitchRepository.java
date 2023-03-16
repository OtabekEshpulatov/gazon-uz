package com.noobs.gazonuz.repositories.pitch;


import com.noobs.gazonuz.domains.Pitch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PitchRepository extends JpaRepository<Pitch, String> {
    @Query( "select count(p) from Pitch p where p.user.username ilike ?1" )
    long countByUsernameAllIgnoreCase(String username);

    @Query( "select p from Pitch  p where p.user.username ilike ?1" )
    List<Pitch> findPitchByUsername(String username , Pageable pageable);


}