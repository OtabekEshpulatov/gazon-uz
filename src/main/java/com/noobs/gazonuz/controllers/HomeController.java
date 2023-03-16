package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Location;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.services.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( value = {"/" ,"/home"})
@RequiredArgsConstructor
public class HomeController {


    private final AuthUserService authUserService;


    @GetMapping
    public String getLogin() {
        return "home/index";
    }

    @PostMapping()
    public String homeForm(@ModelAttribute Location location){
        return "redirect:/pitch/searched";
    }



}
