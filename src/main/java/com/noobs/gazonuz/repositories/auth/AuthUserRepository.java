package com.noobs.gazonuz.repositories.auth;


import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.AuthUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCaseAllIgnoreCase(String username);

    @Query( "select u from User  u order by u.createdAt desc" )
    List<User> getAllOrderByCreatedAtDesc();


    @Query( "select count(*) from User" )
    Long countAllUsers();

    @Transactional
    @Modifying
    @Query( "update User u set u.status = ?1 where u.id = ?2" )
    int updateStatusById(AuthUserStatus status , String id);


    @Override
    Optional<User> findById(String s);

    @Query( value = "SELECT u FROM User u JOIN u.roles r GROUP BY u HAVING COUNT(r) > 0" )
    List<User> findAllUsersWithRoles();

    boolean addRole(String pitchOwner , String id);

//    @Query("insert into  ")
//    boolean addRole(String pitchOwner , String id);


}

