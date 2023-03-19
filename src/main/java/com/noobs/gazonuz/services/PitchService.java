package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.dtos.upload.DocumentCreateDTO;
import com.noobs.gazonuz.enums.PitchStatus;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
            Document doc = documentService.createAndGet(new DocumentCreateDTO(document),user);
            docs.add(doc);
        }

        District district = addressService.getDistrictById(dto.getDistrictId());
        Pitch pitch = Pitch.builder()
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")))
                .fullAddress(dto.getFullAddress())
                .info(dto.getInfo())
                .latitude("111.22")
                .longitude("111.22")
                .name(dto.getName())
                .price(dto.getPrice())
                .documents(docs)
                .district(district)
                .phoneNumber(dto.getPhoneNumber())
                .status(PitchStatus.INACTIVE)
                .user(user)
                .build();
        pitchRepository.save(pitch);
        return true;
    }

    public List<Pitch> getPitches(String latitude, String longitude) {
        return pitchRepository.findAll();
    }

    public static String daySuffix(String day) {
        int dayInt = Integer.parseInt(day);
        switch (dayInt % 10) {
            case 1:
                if (dayInt == 11) {
                    return "th";
                } else {
                    return "st";
                }
            case 2:
                if (dayInt == 12) {
                    return "th";
                } else {
                    return "nd";
                }
            case 3:
                if (dayInt == 13) {
                    return "th";
                } else {
                    return "rd";
                }
            default:
                return "th";
        }
    }
}
