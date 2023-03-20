package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.properties.ApplicationProperties;
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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PitchService {

    private final PitchRepository pitchRepository;
    private final AddressService addressService;
    private final DocumentService documentService;
    private final EmailService emailService;
    private final ApplicationProperties properties;


    public boolean savePitch(PitchCreateDTO dto , User user) {


        ArrayList<Document> docs = new ArrayList<>();
        for ( MultipartFile document : dto.getDocuments() ) {
            Document doc = documentService.createAndGet(new DocumentCreateDTO(document) , user);
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
                .status(PitchStatus.REQUESTED)
                .user(user)
                .build();
        pitchRepository.save(pitch);
        return true;
    }

    public List<Pitch> getPitches(String latitude , String longitude) {
        return pitchRepository.findAll();
    }

    public static String daySuffix(String day) {
        int dayInt = Integer.parseInt(day);
        switch ( dayInt % 10 ) {
            case 1:
                if ( dayInt == 11 ) {
                    return "th";
                } else {
                    return "st";
                }
            case 2:
                if ( dayInt == 12 ) {
                    return "th";
                } else {
                    return "nd";
                }
            case 3:
                if ( dayInt == 13 ) {
                    return "th";
                } else {
                    return "rd";
                }
            default:
                return "th";
        }
    }

    public void updateStatus(String id , PitchStatus status) {


        final Optional<Pitch> pitchOptional = pitchRepository.findById(id);


        pitchOptional.ifPresent(pitch -> {


            final User user = pitch.getUser();
            final String email = user.getEmail();

            if ( user.isEmailNotificationsAllowed() ) {
                switch ( status ) {
                    case BLOCKED -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.blocked.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.blocked.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email , messageBody , messageHeader);
                    }
                    case REJECTED -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.rejected.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.rejected.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email , messageBody , messageHeader);
                    }
                    case ACTIVE -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.active.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.active.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email , messageBody , messageHeader);
                    }
                }
            }

            pitchRepository.updateStatusById(status , id);

        });

    }

}
