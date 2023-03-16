package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.handler.CustomRuntimeException;
import com.noobs.gazonuz.repositories.PitchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
@RequestMapping( "/pitchInfo" )
@RequiredArgsConstructor
public class PitchPostController {


    private final PitchRepo pitchRepo;
    @GetMapping("/{id}")
    public String showPitchInfo(@PathVariable( value = "id") String id, Model model) {

        Optional<Pitch> pitch = pitchRepo.findById(id);
        if(pitch.isPresent()){
            model.addAttribute("pitch", pitch);
            return "pitch/PitchMainForCopy";
        }else{
            throw new CustomRuntimeException("Page not found");
        }

    }
}
