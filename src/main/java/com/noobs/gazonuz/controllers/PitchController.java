package com.noobs.gazonuz.controllers;


import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.services.PitchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/pitch")
public class PitchController {
    private final PitchService pitchService;

    public PitchController(PitchService pitchService) {
        this.pitchService = pitchService;
    }

    @GetMapping("/create")
    public String createPitch(Model model) {
        model.addAttribute("dto", new PitchCreateDTO());
        return "pitch/create";
    }


    @PostMapping("/create")
    public String savePitch(@Valid @ModelAttribute("dto") PitchCreateDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            return "pitch/create";
        }

        PitchCreateDTO build = PitchCreateDTO.builder()
                .name("Bunyodkor")
                .latitude("41.3111")
                .longitude("69.2400")
                .info("info")
                .fullAddress("fullAdres")
                .documents(Collections.emptySet())
                .orders(Collections.emptySet())
                .comments(Collections.emptySet())
                .price(100.5)
                .phoneNumber("9988776655")
                .user(new User())
                .district(new District())
                .build();
        pitchService.savePitch(build);
        return "pitch/create";
    }


}
