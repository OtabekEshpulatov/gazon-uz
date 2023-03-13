package com.noobs.gazonuz.repositories.auth;


import com.noobs.gazonuz.domains.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCaseAllIgnoreCase(String username);


}
