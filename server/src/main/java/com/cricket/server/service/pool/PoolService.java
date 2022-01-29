package com.cricket.server.service.pool;

import com.cricket.server.dao.player.PlayerRepo;
import com.cricket.server.dao.pool.PlayerPoolRowRepo;
import com.cricket.server.dao.pool.PoolRepo;
import com.cricket.server.mappers.PlayerPoolRowMapper;
import com.cricket.server.mappers.PoolMapper;
import com.cricket.server.model.pool.dto.PlayerPoolDto;
import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.exceptions.PlayerDoesNotExistsInPoolException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.model.player.Player;
import com.cricket.server.model.pool.PlayerPoolRow;
import com.cricket.server.model.pool.Pool;
import com.cricket.server.model.pool.dto.PlayerPoolRowDto;
import com.cricket.server.model.pool.dto.PoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoolService implements IPoolService {
    @Autowired
    private PoolRepo poolRepo;

    @Autowired
    private PlayerPoolRowRepo playerPoolRowRepo;

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private PoolMapper poolMapper;

    @Autowired
    private PlayerPoolRowMapper playerPoolRowMapper;

    @Override
    public List<PoolDto> findAllPoolsInAuction(Integer auctionId){
        List<Pool> pools = poolRepo.findPoolsInAuction(auctionId);
        return poolMapper.entityToDtoList(pools);
    }

    @Override
    public PoolDto findPoolInAuction(Integer auctionId, Integer poolId)
            throws PoolDoesNotExistsException {
        Pool poolFound = poolRepo.findPoolInAuction(auctionId, poolId)
                .orElseThrow(() -> new PoolDoesNotExistsException("Given pool does not exists in this auction."));
        return poolMapper.entityToDto(poolFound);
    }

    @Override
    public PlayerPoolRowDto addPlayerToPool(PlayerPoolDto playerToAdd)
            throws PoolDoesNotExistsException, PlayerDoesNotExistsException {
        Pool currentPool = poolRepo.findById(playerToAdd.getPoolID())
                .orElseThrow(() -> new PoolDoesNotExistsException("Pool with poolId: " + playerToAdd.getPoolID() + " does not exists."));
        Player currentPlayer = playerRepo.findById(playerToAdd.getPlayerID())
                .orElseThrow(() -> new PlayerDoesNotExistsException("Player with id: " + playerToAdd.getPlayerID() + " does not exists"));

        PlayerPoolRow playerPoolRow = new PlayerPoolRow();
        playerPoolRow.setPlayer(currentPlayer);
        playerPoolRow.setPool(currentPool);
        playerPoolRow.setBasePrice(playerToAdd.getBasePrice());

        PlayerPoolRow playerRowSaved = playerPoolRowRepo.save(playerPoolRow);
        return playerPoolRowMapper.entityToDto(playerRowSaved);
    }

    @Override
    public void removePlayerFromPool(PlayerPoolDto playerToRemove)
            throws PlayerDoesNotExistsInPoolException {
        if (!playerPoolRowRepo.existsPlayerInPool(playerToRemove.getPoolID(), playerToRemove.getPlayerID())){
            throw new PlayerDoesNotExistsInPoolException("Player does not exists in given pool.");
        }
        playerPoolRowRepo.deletePlayerInPool(playerToRemove.getPoolID(), playerToRemove.getPlayerID());
    }

}
