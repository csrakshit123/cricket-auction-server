package com.cricket.server.mappers;

import com.cricket.server.model.player.Player;
import com.cricket.server.model.pool.PlayerPoolRow;
import com.cricket.server.model.pool.dto.PlayerPoolRowDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", uses = {PlayerMapper.class})
public interface PlayerPoolRowMapper {
    PlayerPoolRowDto entityToDto(PlayerPoolRow playerPoolRow);
    PlayerPoolRow dtoToEntity(PlayerPoolRowDto playerPoolRowDto);

    List<PlayerPoolRowDto> entityToDtoList(List<PlayerPoolRow> playerPoolRowList);
    List<PlayerPoolRow> dtoToEntityList(List<PlayerPoolRowDto> playerPoolRowDtoList);


}
