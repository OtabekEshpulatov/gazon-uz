package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.services.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/auth" )
@RequiredArgsConstructor
public class AuthController {


    private final AuthUserService authUserService;


    @GetMapping( "/login" )
    public ModelAndView getLogin(@RequestParam( name = "error", required = false ) String error) {
        var mav = new ModelAndView();
        mav.addObject("error" , error);
        mav.setViewName("auth/login");
        return mav;
    }





    @GetMapping( "/register" )
    public String getRegister(Model model) {
        model.addAttribute("dto" , new UserCreatedDto());
        return "auth/register";
    }


    @GetMapping( "/logout" )
    public ModelAndView getLogout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("auth/logout");
        return mav;
    }

    @PostMapping( "/register" )
    public String register(@Valid @ModelAttribute( "dto" ) UserCreatedDto dto , BindingResult bindingResult) {

        System.err.println(bindingResult.getAllErrors());

        if ( bindingResult.hasErrors() ) {
            return "auth/register";
        }


        for ( ObjectError allError : bindingResult.getAllErrors() ) {
            System.err.println("\n %s" + allError);
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
