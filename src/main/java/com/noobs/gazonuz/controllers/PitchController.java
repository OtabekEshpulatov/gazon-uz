package com.noobs.gazonuz.controllers;

import com.google.gson.Gson;
import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Location;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.DistrictDto;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import com.noobs.gazonuz.mappers.DistrictMapper;
import com.noobs.gazonuz.repositories.PitchPaginationRepository;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import com.noobs.gazonuz.services.AddressService;
import com.noobs.gazonuz.services.PitchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pitch")
@AllArgsConstructor
public class PitchController {
    private final PitchService pitchService;
    private final UserSession userSession;
    private final AddressService addressService;
    private final DistrictMapper districtMapper;
    private final PitchRepository pitchRepository;
    private final PitchPaginationRepository pitchPaginationRepository;


    @GetMapping("/create")
    public ModelAndView createPitch(@RequestParam(name = "eror", required = false) String error, ModelAndView modelAndView) {
        modelAndView.addObject("error", error);
        modelAndView.addObject("pitch", new PitchCreateDTO());
        modelAndView.addObject("regions", addressService.getRegion());
        modelAndView.setViewName("/pitch/create");
        return modelAndView;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("pitch") PitchCreateDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            return "/pitch/create";
        }
        System.out.println("dto.getDistrictId() = " + dto.getDistrictId());
        dto.setDistrictId("1");
        pitchService.savePitch(dto, userSession.getUser());
        return "redirect:/home";
    }


    //  2 version
    @GetMapping("/create2")
    public ModelAndView createPitch2(@RequestParam(name = "eror", required = false) String error, ModelAndView modelAndView) {
        modelAndView.addObject("error", error);
        modelAndView.addObject("pitch", new PitchCreateDTO());
        modelAndView.addObject("regions", addressService.getRegion());
        modelAndView.addObject("districts", new District());
        modelAndView.setViewName("/pitch/create2");
        return modelAndView;
    }


    @PostMapping("/create2")
    public String create2(@ModelAttribute("pitch") PitchCreateDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            return "/pitch/create2";
        }
        System.out.println("dto.getDistrictId() = " + dto.getDistrictId());
//        dto.setDistrictId("1");
//        pitchService.savePitch(dto, userSession.getUser());
        return "redirect:/home";
    }


    @GetMapping(value = "/districts/{regionId}", produces = "application/json")
    public ResponseEntity<?> getDistricts(@PathVariable String regionId) {
        List<District> districts = addressService.getDistricts(regionId);


        List<DistrictDto> districtDtos = new ArrayList<>();

        for (District district : districts) {
            DistrictDto e = districtMapper.toDto(district);
            districtDtos.add(e);
            System.out.println("e = " + e);
        }

        Gson gson = new Gson();


        String s = null;
        try {
            gson.toJson(districtDtos.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);

        return ResponseEntity.ok().body(s);


    }


    @GetMapping("/pitches")
    public List<Pitch> getPitches(@RequestParam(name = "longitude") String longitude,
                                  @RequestParam(name = "latitude") String latitude) {


        throw new RuntimeException("my ex");
//        return pitchService.getPitches(latitude , longitude);
    }

    @GetMapping("/searched")
    public ModelAndView searchUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "perPage", defaultValue = "10") int perPage,
                                    @RequestParam(name = "search", defaultValue = "") String search,
                                    @ModelAttribute Location location) {

        //to PitchService connection
        List<Pitch> searchedPitches = pitchService.getSearchedPitches(location, page, perPage, search);
        long numPitches = pitchService.getSize(location, search);
        // end PitchService connection

        //mav preparation
        var mav = new ModelAndView();
        mav.addObject("pitches", searchedPitches);
        mav.addObject("currentPage", page);
        mav.addObject("totalFound",numPitches);
        mav.addObject("totalPage", numPitches / PitchPaginationRepository.PER_PAGE);
        mav.addObject("perPage", PitchPaginationRepository.PER_PAGE);
        mav.addObject("search", search);
        mav.setViewName("/pitch/searched");
        //end mav preparation

        return mav;
    }


}
