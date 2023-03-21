package com.noobs.gazonuz.domains;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Location {
    private Double longitude;
    private Double latitude;
    private String address;


}
