package com.noobs.gazonuz.dtos;

import com.noobs.validators.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
@Getter
public class UserCreatedDto {
    @Unique( tableName = "Users", columnName = "email", message = "username.is.already.taken" ) String username;
    @Email( message = "Invalid email" ) String email;
    @Size( min = 8, max = 15, message = "Min password size should be {min} and max {max}" )
    String password;
    @Size( min = 8, max = 15, message = "Min password size should be {min} and max {max}" )
    String confirmPassword;


}