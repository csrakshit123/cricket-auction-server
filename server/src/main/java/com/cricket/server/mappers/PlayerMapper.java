package com.cricket.server.mappers;

import com.cricket.server.model.player.Player;
import com.cricket.server.model.player.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player dtoToEntity(PlayerDto playerDto);
    PlayerDto entityToDto(Player player);

    List<Player> dtoToEntityList(List<PlayerDto> playerDtoList);
    List<PlayerDto> entitToDtoList(List<Player> playerList);
}
