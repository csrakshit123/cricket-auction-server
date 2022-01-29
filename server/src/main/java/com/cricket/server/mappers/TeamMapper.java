package com.cricket.server.mappers;

import com.cricket.server.model.team.Team;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import com.cricket.server.model.team.dto.TeamDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDto entityToDto(Team team);
    Team dtoToEntity(TeamDto teamDto);

    List<TeamSummaryDto> entityToDtoList(List<Team> teamList);
    List<Team> dtoToEntityList(List<TeamSummaryDto> teamSummaryDtoList);
}
