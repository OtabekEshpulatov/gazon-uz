package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.properties.ApplicationProperties;
import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.dtos.PitchOrderTimeDTO;
import com.noobs.gazonuz.dtos.upload.DocumentCreateDTO;
import com.noobs.gazonuz.enums.PitchStatus;
import com.noobs.gazonuz.repositories.OrderDAO;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final OrderDAO orderDAO;
    private final EmailService emailService;

    private final ApplicationProperties properties;


    public boolean savePitch(PitchCreateDTO dto, User user) {


        ArrayList<Document> docs = new ArrayList<>();
        for (MultipartFile document : dto.getDocuments()) {
            Document doc = documentService.createAndGet(new DocumentCreateDTO(document), user);
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

    public static PitchOrderTimeDTO findDateInterval(long i, long k) {
        String result = "";
        i = i - 1;
        PitchOrderTimeDTO pitchOrderTimeDTO = new PitchOrderTimeDTO();
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Tashkent"));
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime currentDate = LocalDateTime.of(localDate, localTime);
        if (currentDate.plusDays(k - 1).plusHours(i).isAfter(currentDateTime)) {
            if (i < 10) {
                result += "0" + i + ":00-";
            } else {
                result += i + ":00-";
            }
            if (i + 1 >= 10) {
                result += i + 1 + ":00";
            } else {
                result += "0" + (i + 1) + ":00";
            }
            pitchOrderTimeDTO.setIsAvl(true);
        } else {
            pitchOrderTimeDTO.setIsAvl(false);
        }
        pitchOrderTimeDTO.setMessage(result);
        return pitchOrderTimeDTO;
    }

    public void updateStatus(String id, PitchStatus status) {


        final Optional<Pitch> pitchOptional = pitchRepository.findById(id);


        pitchOptional.ifPresent(pitch -> {


            final User user = pitch.getUser();
            final String email = user.getEmail();

            if (user.getIsEmailNotificationsAllowed()) {
                switch (status) {
                    case BLOCKED -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.blocked.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.blocked.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email, messageBody, messageHeader);
                    }
                    case REJECTED -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.rejected.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.rejected.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email, messageBody, messageHeader);
                    }
                    case ACTIVE -> {
                        final String messageBody = properties.getProperties().getProperty("pitch.status.active.message.body").formatted(pitch.getCreatedAt());
                        final String messageHeader = properties.getProperties().getProperty("pitch.status.active.message.header");
                        emailService.sendMessageToEmailThroughSMTP(email, messageBody, messageHeader);
                    }
                }
            }

            pitchRepository.updateStatusById(status, id);

        });

    }

    public Boolean checkBooked(long i, long k, String pitchId){
        List<Order> orderList = orderDAO.findAllAcceptedOrdersByPitchId(pitchId);
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime currentDate = LocalDateTime.of(localDate,localTime).plusDays(k-1).plusHours(i-1);
        for (Order order : orderList) {
            if (order.getStartTime().isBefore(currentDate.plusMinutes(5)) &&
                    order.getStartTime().plusMinutes(order.getMinutes()).isAfter(currentDate)){
                return false;
            }
        }
        return true;
    }
}
