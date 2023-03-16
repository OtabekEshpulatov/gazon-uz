package com.noobs.gazonuz.repositories;

import com.noobs.gazonuz.domains.Pitch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitchPaginationRepository extends JpaRepository<Pitch, String> {
   int PER_PAGE = 10;
   @Query("select p from Pitch p where  p.latitude>=:minLatitude and p.latitude<=:maxLatitude and p.longitude>=:minLongitude and p.longitude<=:maxLongitude")
   List<Pitch> pitches(@Param("minLatitude") Double minLatitude,@Param("maxLatitude") Double maxLatitude, @Param("minLongitude") Double minLongitude,@Param("maxLongitude") Double maxLongitude, Pageable pageable);
   @Query( "select count(p) from Pitch p where upper(p.name) like upper(concat('%', ?1, '%'))")
   Long countPitchesThatMatch(String name);

}
