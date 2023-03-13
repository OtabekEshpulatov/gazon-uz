package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.mappers.AuthUserMapper;
import com.noobs.gazonuz.services.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/auth" )
@RequiredArgsConstructor
public class AuthController {


    private final AuthUserService authUserService;


    @GetMapping( "/login" )
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping( "/register" )
    public String getRegister(Model model) {
        model.addAttribute("dto" , new UserCreatedDto());
        return "auth/register";
    }

    @PostMapping( "/register" )
    public String register(@Valid @ModelAttribute( "dto" ) UserCreatedDto dto , BindingResult bindingResult) {


        if ( bindingResult.hasErrors() ) {
            System.err.println(bindingResult.getAllErrors());
//            return "auth/register";
        }

        if ( !dto.getPassword().equals(dto.getConfirmPassword()) ) {
            bindingResult.rejectValue("password" , "" , "passwords.dont.match");
            bindingResult.rejectValue("confirmPassword" , "" , "passwords.dont.match");
            return "auth/register";
        }

        authUserService.saveUser(dto);

        return "auth/login";
    }

}
