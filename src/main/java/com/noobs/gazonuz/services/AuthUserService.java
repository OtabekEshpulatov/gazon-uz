package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.mappers.AuthUserMapper;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.validators.AuthValidator;
import com.noobs.payload.Response;
import jakarta.validation.ConstraintViolation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.Set;

@Service
@RequiredArgsConstructor
//@NoArgsConstructor
public class AuthUserService {

    private final AuthValidator authValidator;
    private final AuthUserMapper mapper;
    private final AuthUserRepository authUserRepository;


    public Response<Set<ConstraintViolation<UserCreatedDto>>> validate(UserCreatedDto dto) {
        final boolean exist = authUserRepository.existsByUsernameIgnoreCaseAllIgnoreCase(dto.getUsername());
        final Set<ConstraintViolation<UserCreatedDto>> validate = authValidator.validate(dto);

        if ( exist ) {
            return new Response<>(validate , "username already exists" , "" , false);
        }

//        if(validate)
//        return new Response<>(validate , "something went wrong" , "" , false);

        return null;
    }

    public boolean existsWithUsername(String username) {
        return authUserRepository.existsByUsernameIgnoreCaseAllIgnoreCase(username);
    }

    public boolean saveUser(UserCreatedDto dto) {
        final User user = mapper.fromCreateDto(dto);
        authUserRepository.save(user);
        return true;
    }
}
