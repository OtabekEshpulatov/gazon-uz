package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.repositories.pagination.UserPaginationRepo;
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

    public AdminController(UserSession userSession , AuthUserService userService , UserPaginationRepo userPaginationRepo , AuthUserRepository authUserRepository) {
        this.userSession = userSession;
        this.userService = userService;
        this.userPaginationRepo = userPaginationRepo;
        this.authUserRepository = authUserRepository;
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

    private void preparePageableMAV(Integer page , ModelAndView mav , List<User> users , Long usersSize , String baseUrl) {
        mav.addObject("users" , users);
        mav.addObject("statuses" , Arrays.asList(AuthUserStatus.values()));
        mav.addObject("currentPage" , page);
        mav.addObject("totalPage" , usersSize / UserPaginationRepo.PER_PAGE);
        mav.addObject("perPage" , UserPaginationRepo.PER_PAGE);
        mav.addObject("search" , baseUrl);
        mav.setViewName("manage/users");
    }

    @GetMapping( "/edituser/status" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public String editUserStatus(@RequestParam( "id" ) String id ,
                                 @RequestParam( "page" ) Integer page ,
                                 @RequestParam( "perPage" ) Integer perPage ,
                                 @RequestParam( "status" ) AuthUserStatus status ,
                                 @RequestParam( value = "search", required = false )
                                 String search) {
        authUserRepository.updateStatusById(status , id);
        return "redirect:/manage/users?page=%d&perPage=%d&search=%s".formatted(page , perPage , search);

    }


    @GetMapping( "/users" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ModelAndView searchUsers(@RequestParam( name = "page", defaultValue = "1" ) int page ,
                                    @RequestParam( name = "perPage", defaultValue = "10" ) int perPage ,
                                    @RequestParam( name = "search", defaultValue = "" ) String search) {
        var mav = new ModelAndView();

        List<User> users;
        final Pageable pageable = PageRequest.of(page , perPage);
        final String searchBy = "%" + search + "%";
        users = userPaginationRepo.findByUsernameContainsIgnoreCase(searchBy , pageable);
        final Long userSize = userPaginationRepo.countUsersThatMatches(searchBy);
        preparePageableMAV(page , mav , users , userSize , search);
        System.out.println(users);
        return mav;
    }
}
