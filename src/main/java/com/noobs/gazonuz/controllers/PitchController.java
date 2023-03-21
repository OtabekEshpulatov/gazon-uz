package com.noobs.gazonuz.controllers;


import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.dtos.DistrictDto;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.mappers.DistrictMapper;
import com.noobs.gazonuz.services.AddressService;
import com.noobs.gazonuz.services.PitchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/pitch")
public class PitchController {
    private final PitchService pitchService;
    private final UserSession userSession;
    private final AddressService addressService;



    @GetMapping("/create")
    public ModelAndView createPitch(@RequestParam(name = "eror", required = false) String error, ModelAndView modelAndView) {
        modelAndView.addObject("error", error);
        modelAndView.addObject("pitch", new PitchCreateDTO());
        modelAndView.addObject("regions", addressService.getRegion());
        modelAndView.addObject("districts", new DistrictDto());
        modelAndView.setViewName("/pitch/create");
        return modelAndView;
    }


    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("pitch") PitchCreateDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            return "/pitch/create";
        }
//        pitchService.savePitch(dto, userSession.getUser());
        return "redirect:/home";
    }


    @GetMapping(value = "/districts/{regionId}", produces = "application/json")
    public ResponseEntity<?> getDistricts(@PathVariable String regionId) {
        String districts = addressService.getDistricts(regionId);
        return ResponseEntity.ok().body(districts);
    }


    @GetMapping("/pitches")
    public List<Pitch> getPitches(@RequestParam(name = "longitude") String longitude,
                                  @RequestParam(name = "latitude") String latitude) {


        throw new RuntimeException("my ex");
//        return pitchService.getPitches(latitude , longitude);
    }


}
