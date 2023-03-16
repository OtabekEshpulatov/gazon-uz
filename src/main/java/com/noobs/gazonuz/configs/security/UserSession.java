package com.noobs.gazonuz.configs.security;

import com.noobs.gazonuz.domains.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var authUserDetails = authentication.getPrincipal();
<<<<<<< HEAD
        if ( authUserDetails instanceof AuthUserDetails a )
=======
        if (authUserDetails instanceof AuthUserDetails a)
>>>>>>> origin/main
            return a.getAuthUser();
        return null;
    }

    public String getId() {
        User user = getUser();
<<<<<<< HEAD
        if ( user != null )
=======
        if (user != null)
>>>>>>> origin/main
            return user.getId();
        return null;
    }
}
