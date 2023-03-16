package com.noobs.gazonuz.controllers;


import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.services.AddressService;
import com.noobs.gazonuz.services.PitchService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/pitch")
public class PitchController {
    private final PitchService pitchService;
    private final UserSession userSession;
    private final AddressService addressService;

    public PitchController(PitchService pitchService, UserSession userSession, AddressService addressService) {
        this.pitchService = pitchService;
        this.userSession = userSession;
        this.addressService = addressService;
    }



    @GetMapping("/create")
    public String createPitch(Model model) {
        model.addAttribute("pitch", new PitchCreateDTO());
        model.addAttribute("regions", addressService.getRegion());
        model.addAttribute("dto", new PitchCreateDTO());

        return "/pitch/create";
    }


    @PostMapping("/create")
    public String savePitch(@Valid @ModelAttribute("pitch") PitchCreateDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            return "/pitch/create";
        }

        PitchCreateDTO build = PitchCreateDTO.builder()
                .name(dto.getName())
                .latitude("41.3111")
                .longitude("69.2400")
                .info(dto.getInfo())
                .fullAddress(dto.getFullAddress())
                .documents(Collections.emptySet())
                .price(dto.getPrice())
                .phoneNumber(dto.getPhoneNumber())
                .district(dto.getDistrict()) // todo
                .user(userSession.getUser())
                .build();

        System.out.println("build = " + build);

//        pitchService.savePitch(build);
        return "/pitch/create";
    }


    @GetMapping("/pitches")
    public List<Pitch> getPitches(@RequestParam(name = "longitude") String longitude,
                                  @RequestParam(name = "latitude") String latitude) {


        throw new RuntimeException("my ex");
//        return pitchService.getPitches(latitude , longitude);
    }


}
