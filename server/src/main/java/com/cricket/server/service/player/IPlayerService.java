package com.cricket.server.service.player;

import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.model.player.Player;
import com.cricket.server.model.player.dto.PlayerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPlayerService {
    List<PlayerDto> findAll();

    PlayerDto findById(Integer playerId) throws PlayerDoesNotExistsException;

    PlayerDto create(PlayerDto playerDto);

    PlayerDto update(PlayerDto playerDto)
            throws PlayerDoesNotExistsException;

    void delete(Integer playerId)
            throws PlayerDoesNotExistsException;
}
