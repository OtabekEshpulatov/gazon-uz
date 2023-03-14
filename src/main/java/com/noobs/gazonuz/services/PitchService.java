package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PitchService {
    private final PitchRepository pitchRepository;


    public List<Pitch> getPitches(String latitude , String longitude) {
        return pitchRepository.findAll();
    }
}