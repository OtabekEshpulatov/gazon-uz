package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.dtos.PitchCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PitchMapper {

    Pitch fromCreateDto(PitchCreateDTO createdDto);
}
