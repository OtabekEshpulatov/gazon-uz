package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.mappers.PitchMapper;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PitchService {

    private final PitchRepository pitchRepository;
    private final PitchMapper pitchMapper;


    public boolean savePitch(PitchCreateDTO dto) {
        final Pitch pitch = pitchMapper.fromCreateDto(dto);
        pitchRepository.save(pitch);
        return true;
    }


}
