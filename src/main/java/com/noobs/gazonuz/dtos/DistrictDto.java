package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.domains.location.Region;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Builder
@Data
public class DistrictDto {
    String id;
    String name;

//    private Region region;

}
