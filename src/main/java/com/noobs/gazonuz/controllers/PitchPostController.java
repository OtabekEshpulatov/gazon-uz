package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.OrderCreateDTO;
import com.noobs.gazonuz.handler.CustomRuntimeException;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @PostMapping("/orderSend")
    public String create(@Valid @ModelAttribute() OrderCreateDTO dto, BindingResult bindingResult) {

//        ModelAndView modelAndView = new ModelAndView();
//        if (bindingResult.hasErrors()) {
//            System.err.println(bindingResult.getAllErrors());
//            modelAndView.setViewName("/pitch/create");
//            return modelAndView;
//        }
//        pitchService.savePitch(dto, userSession.getUser());
//        modelAndView.setViewName("redirect:pitch/PitchMainForCopy");
        return "pitch/PitchMainForCopy";
    }
}
