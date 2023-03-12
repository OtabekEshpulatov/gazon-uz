package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.dtos.UserCreatedDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;

@Controller
@RequestMapping( "/auth" )
public class AuthController {


    @GetMapping( "/login" )
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping( "/register" )
    public String getRegister() {
        return "auth/register";
    }

    @PostMapping( "/register" )
    public String register(@Valid @ModelAttribute UserCreatedDto dto , BindingResult bindingResult) {

        if ( bindingResult.hasErrors() ) {
            System.err.println(bindingResult.getAllErrors());
            
        }


        return "auth/register";
    }

}
