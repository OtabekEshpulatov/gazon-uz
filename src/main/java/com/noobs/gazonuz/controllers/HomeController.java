package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.services.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( value = {"/" ,"/home"})
@RequiredArgsConstructor
public class HomeController {


    private final AuthUserService authUserService;


    @GetMapping
    public String getLogin() {
        return "home/index";
    }




}
