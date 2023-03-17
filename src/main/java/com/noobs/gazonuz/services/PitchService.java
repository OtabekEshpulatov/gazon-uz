package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.dtos.upload.DocumentCreateDTO;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PitchService {

    private final PitchRepository pitchRepository;
    private final AddressService addressService;
    private final DocumentService documentService;


    public boolean savePitch(PitchCreateDTO dto, User user) {


        ArrayList<Document> docs = new ArrayList<>();
        for (MultipartFile document : dto.getDocuments()) {
            Document doc = documentService.createAndGet(new DocumentCreateDTO(document));
            docs.add(doc);
        }
        String id = dto.getDistrictId();

        District district = addressService.getDistrictById(id);
        Pitch pitch = Pitch.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .fullAddress(dto.getFullAddress())
                .documents(docs)
                .info(dto.getInfo())
                .latitude("111.22")
                .longitude("111.22")
                .district(district)
                .phoneNumber(dto.getPhoneNumber())
                .user(user)
                .build();
        pitchRepository.save(pitch);
        return true;
    }

    public List<Pitch> getPitches(String latitude, String longitude) {
        return pitchRepository.findAll();
    }
}
