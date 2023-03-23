package com.noobs.gazonuz.repositories.pitch;


import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.enums.PitchStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PitchRepository extends JpaRepository<Pitch, String> {
    List<Pitch> findByDistrict(District district);

    @Query("select count(p) from Pitch p where p.user.username ilike ?1 and p.status=?2")
    long countByUsernameAllIgnoreCase(String username, PitchStatus status);

    @Query("select p from Pitch  p where p.user.username ilike ?1 and p.status=?2 order by p.createdAt")
    List<Pitch> findPitchByUsernameAAndStatus(String username, PitchStatus status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Pitch p set p.status = ?1 where p.id = ?2")
    int updateStatusById(PitchStatus status, String id);

    @Query("select p from Pitch p where p.status = ?1 order by p.createdAt")
    List<Pitch> findByStatus(PitchStatus status);

    @Query("select p.user from Pitch p where p.id=?1")
    User findUserByPitch(String id);



    @Query("select p from Pitch p where p.id=?1")
    Pitch getPitch(String pitchId);
}