package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.services.AuthUserService;
import com.noobs.gazonuz.services.PitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/app" )
@RequiredArgsConstructor
public class PitchController {


    private final PitchService pitchService;


    @GetMapping( "/pitches" )
    public List<Pitch> getPitches(@RequestParam( name = "longitude" ) String longitude ,
                                  @RequestParam( name = "latitude" ) String latitude) {


        throw new RuntimeException("my ex");
//        return pitchService.getPitches(latitude , longitude);
    }


}
