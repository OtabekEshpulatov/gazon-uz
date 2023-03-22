package com.noobs.gazonuz.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderCreateDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Order Date time cannot be blank")
    private String orderDatetime;
    @NotBlank(message = "Duration cannot be blank")
    private String duration;
}
