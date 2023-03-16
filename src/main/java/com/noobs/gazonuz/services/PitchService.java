package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.repositories.PitchPaginationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PitchService {
    private final PitchPaginationRepository pitchRepository;


    public List<Pitch> getPitches(String latitude, String longitude) {
        return pitchRepository.findAll();
    }

}

