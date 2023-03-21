package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PitchMapper {


    default Pitch fromCreateDto(PitchCreateDTO createdDto) {
        Pitch pitch = new Pitch();
        pitch.setFullAddress(createdDto.getFullAddress());
        pitch.setLongitude(Double.valueOf(createdDto.getLongitude()));
        pitch.setLatitude(Double.valueOf(createdDto.getLatitude()));
        pitch.setName(createdDto.getName());
        pitch.setPhoneNumber(createdDto.getPhoneNumber());
        pitch.setPrice(createdDto.getPrice());
        pitch.setInfo(createdDto.getInfo());
        pitch.setUser(createdDto.getUser());
        return pitch;
    }


}
