package com.cricket.server.service.player;

import com.cricket.server.dao.player.PlayerRepo;
import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.mappers.PlayerMapper;
import com.cricket.server.model.player.Player;
import com.cricket.server.model.player.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> findAll(){
        List<Player> players = playerRepo.findAll();
        return playerMapper.entitToDtoList(players);
    }

    @Override
    public PlayerDto findById(Integer playerId) throws PlayerDoesNotExistsException {
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new PlayerDoesNotExistsException("Player with id: " + playerId + " does not exists"));
        return playerMapper.entityToDto(player);
    }

    @Override
    public PlayerDto create(PlayerDto playerDto){
        Player playerToSave = playerMapper.dtoToEntity(playerDto);
        Player createdPlayer = playerRepo.save(playerToSave);
        return playerMapper.entityToDto(createdPlayer);
    }

    @Override
    public PlayerDto update(PlayerDto playerDto)
            throws PlayerDoesNotExistsException {
        if (!playerRepo.existsById(playerDto.getPlayerId())){
            throw new PlayerDoesNotExistsException("Player with id: " + playerDto.getPlayerId() + " does not exists");
        }
        Player playerToUpdate = playerMapper.dtoToEntity(playerDto);
        Player updatedPlayer = playerRepo.save(playerToUpdate);
        return playerMapper.entityToDto(updatedPlayer);
    }

    @Override
    public void delete(Integer playerId)
            throws PlayerDoesNotExistsException {
        if (!playerRepo.existsById(playerId)){
            throw new PlayerDoesNotExistsException("Player with id: " + playerId + " does not exists");
        }
        playerRepo.deleteById(playerId);
    }
}
