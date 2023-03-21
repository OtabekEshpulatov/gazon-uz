package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.handler.CustomRuntimeException;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping( "/pitchInfo" )
@RequiredArgsConstructor
public class PitchPostController {


    private final PitchRepository pitchRepo;

    @GetMapping( "/{id}" )
    public String showPitchInfo(@PathVariable( value = "id" ) String id , Model model) {
        Optional<Pitch> pitch = pitchRepo.findById(id);
        if ( pitch.isPresent() ) {
            LocalDate startDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
            LocalDate endDate = startDate.plusDays(6);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMMM");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);

            String dateRange = formattedStartDate + " - " + formattedEndDate;

            List<LocalDate> dates = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                dates.add(startDate.plusDays(i));
            }
            model.addAttribute("datesForPitch", dates);
            model.addAttribute("dateRange", dateRange);
            model.addAttribute("pitch" , pitch.get());
            return "pitch/PitchMainForCopy";
        } else {
            throw new CustomRuntimeException("Page not found");
        }
    }
}
