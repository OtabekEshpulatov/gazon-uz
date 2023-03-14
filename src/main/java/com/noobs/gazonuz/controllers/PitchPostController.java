package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.repositories.PitchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/pithInfo/" )
@RequiredArgsConstructor
public class PitchPostController {
    @GetMapping("/pitchInfo/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        Pitch pitch = PitchRepo.(id);
        model.addAttribute("pitch", pitch);
        return "update_course";
    }
}
