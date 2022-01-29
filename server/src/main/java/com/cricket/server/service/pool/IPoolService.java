package com.cricket.server.service.pool;

import com.cricket.server.model.pool.dto.PlayerPoolDto;
import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.exceptions.PlayerDoesNotExistsInPoolException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.model.pool.dto.PlayerPoolRowDto;
import com.cricket.server.model.pool.dto.PoolDto;

import java.util.List;

public interface IPoolService {
    List<PoolDto> findAllPoolsInAuction(Integer auctionId);

    PoolDto findPoolInAuction(Integer auctionId, Integer poolId)
            throws PoolDoesNotExistsException;

    PlayerPoolRowDto addPlayerToPool(PlayerPoolDto playerToAdd)
            throws PoolDoesNotExistsException, PlayerDoesNotExistsException;

    void removePlayerFromPool(PlayerPoolDto playerToRemove)
            throws PlayerDoesNotExistsInPoolException;
}
