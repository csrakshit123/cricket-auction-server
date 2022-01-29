package com.cricket.server.mappers;

import com.cricket.server.model.participation.Participation;
import com.cricket.server.model.participation.dto.ParticipationDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    List<ParticipationDto> entityToDtoList(List<Participation> participationList);
    List<Participation> dtoToEntityList(List<ParticipationDto> participationDtoList);
    Participation dtoToEntity(ParticipationDto participationDto);
    ParticipationDto entityToDto(Participation participation);

}
