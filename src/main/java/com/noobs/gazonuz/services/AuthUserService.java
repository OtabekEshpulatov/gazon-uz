package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.security.Encoders;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.mappers.AuthUserMapper;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.validators.AuthValidator;
import com.noobs.payload.Response;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
//@NoArgsConstructor
public class AuthUserService {

    private final AuthValidator authValidator;
    private final AuthUserMapper mapper;
    private final AuthUserRepository authUserRepository;

    private final Encoders encoders;


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
        user.setPassword(encoders.passwordEncoder().encode(dto.getPassword()));
        user.setStatus(AuthUserStatus.ACTIVE);
        authUserRepository.save(user);
        return true;
    }


    public List<User> getAllOrderByCreatedAt() {
        return authUserRepository.getAllOrderByCreatedAtDesc();
    }
}
