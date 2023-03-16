package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.repositories.pagination.UserPaginationRepo;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import com.noobs.gazonuz.services.AuthUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping( "/manage" )
@EnableMethodSecurity
@EnableWebSecurity
public class AdminController {


    private final UserSession userSession;
    private final AuthUserService userService;
    private final UserPaginationRepo userPaginationRepo;
    private final AuthUserRepository authUserRepository;
    private final PitchRepository pitchRepository;

    public AdminController(UserSession userSession , AuthUserService userService , UserPaginationRepo userPaginationRepo , AuthUserRepository authUserRepository , PitchRepository pitchRepository) {
        this.userSession = userSession;
        this.userService = userService;
        this.userPaginationRepo = userPaginationRepo;
        this.authUserRepository = authUserRepository;
        this.pitchRepository = pitchRepository;
    }


//    @GetMapping( "/users" )
//    @PreAuthorize( "hasRole('ADMIN')" )
//    public ModelAndView getAdminPage(@RequestParam( value = "perPage", required = false, defaultValue = "10" ) Integer perPage , @RequestParam( value = "page", required = false, defaultValue = "1" ) Integer page) {
//
//        var mav = new ModelAndView();
//        List<User> users;
//        final Pageable pageable;
//
//        if ( AdminValidator.isNotNullAndGreaterThan0(perPage) && AdminValidator.isNotNullAndGreaterThan0(page) ) {
//            pageable = PageRequest.of(page , perPage);
//            users = userPaginationRepo.findAll(pageable).getContent();
//        } else {
//            pageable = PageRequest.ofSize(20);
//            users = userPaginationRepo.findAll(pageable).getContent();
//        }
//
//        final Long userSize = authUserRepository.countAllUsers();
//        preparePageableMAV(page , mav , users , userSize , "/manage/users");
//        System.out.println(users);
//
//
//        return mav;
//
//    }

    private void preparePageableMAV(Integer page , ModelAndView mav , List<?> users , Long usersSize , String search , String viewName) {
        mav.addObject("objects" , users);
        mav.addObject("statuses" , Arrays.asList(AuthUserStatus.values()));
        mav.addObject("currentPage" , page);
        mav.addObject("totalPage" , usersSize / UserPaginationRepo.PER_PAGE);
        mav.addObject("perPage" , UserPaginationRepo.PER_PAGE);
        mav.addObject("search" , search);
        mav.setViewName(viewName);
    }

    @GetMapping( "/edituser/status" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public String editUserStatus(@RequestParam( "id" ) String id , @RequestParam( "page" ) Integer page , @RequestParam( "perPage" ) Integer perPage , @RequestParam( "status" ) AuthUserStatus status , @RequestParam( value = "search", required = false ) String search) {
        authUserRepository.updateStatusById(status , id);
        return "redirect:/manage/users?page=%d&perPage=%d&search=%s".formatted(page , perPage , search);

    }


    @GetMapping( "/users" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ModelAndView searchUsers(@RequestParam( name = "page", defaultValue = "0" ) int page , @RequestParam( name = "perPage", defaultValue = "10" ) int perPage , @RequestParam( name = "search", defaultValue = "" ) String search) {
        var mav = new ModelAndView();

        List<User> users;
        final Pageable pageable = PageRequest.of(page , perPage);
        final String searchBy = "%" + search + "%";
        users = userPaginationRepo.findByUsernameContainsIgnoreCase(searchBy , pageable);
        final Long userSize = userPaginationRepo.countUsersThatMatches(searchBy);
        preparePageableMAV(page , mav , users , userSize , search , "manage/users");
        System.out.println(users);
        return mav;
    }

    @GetMapping( "/pitches" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ModelAndView searchPitches(@RequestParam( name = "page", defaultValue = "0" ) int page , @RequestParam( name = "perPage", defaultValue = "10" ) int perPage , @RequestParam( name = "search", defaultValue = "" ) String username) {
        var mav = new ModelAndView();

        List<Pitch> pitches;
        final Pageable pageable = PageRequest.of(page , perPage);
        final String searchBy = "%" + username + "%";
        pitches = pitchRepository.findPitchByUsername(searchBy , pageable);
        final Long userSize = pitchRepository.countByUsernameAllIgnoreCase(searchBy);
        preparePageableMAV(page , mav , pitches , userSize , username , "manage/pitches");
        System.out.println(pitches);
        return mav;
    }

}
