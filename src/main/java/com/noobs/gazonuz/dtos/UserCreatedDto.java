package com.noobs.gazonuz.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreatedDto(
        String username ,
        @Pattern( regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$" ,message = "Invalid email") String email ,
        @Size( min = 8, max = 15, message = "Min password size should be {min} and max {max}" )
        String password ,
        @Size( min = 8, max = 15, message = "Min password size should be {min} and max {max}" )
        String confirmPassword ) {
}
