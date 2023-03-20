package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.domains.location.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DistrictDto {


    private String id;
    private String name;
    private Region region;

}
