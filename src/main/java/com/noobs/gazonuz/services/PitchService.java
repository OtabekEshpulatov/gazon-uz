package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.mappers.PitchMapper;
import com.noobs.gazonuz.repositories.adress.DistrictRepository;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PitchService {

    private final PitchRepository pitchRepository;
    private final PitchMapper pitchMapper;
    private final AuthUserRepository authUserRepository;
    private final DistrictRepository districtRepository;


    public boolean savePitch(PitchCreateDTO dto) {
         Pitch pitch = pitchMapper.fromCreateDto(dto);

        Optional<User> user = authUserRepository.findById(pitch.getUser().getId());
        pitch.setUser(user.orElse(pitch.getUser()));
        districtRepository.findById(dto.getDistrict()).ifPresent(pitch::setDistrict);
        pitchRepository.save(pitch);
        return true;
    }
    public List<Pitch> getPitches(String latitude, String longitude) {
        return pitchRepository.findAll();
    }
}
