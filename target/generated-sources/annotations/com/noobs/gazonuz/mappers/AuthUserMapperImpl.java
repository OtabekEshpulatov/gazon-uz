package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T13:03:45+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class AuthUserMapperImpl implements AuthUserMapper {

    @Override
    public User fromCreateDto(UserCreatedDto createdDto) {
        if ( createdDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( createdDto.getUsername() );
        user.email( createdDto.getEmail() );
        user.password( createdDto.getPassword() );

        return user.build();
    }
}
