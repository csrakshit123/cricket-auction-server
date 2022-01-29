package com.cricket.server.dao.pool;

import com.cricket.server.model.pool.PlayerPoolRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPoolRowRepo extends JpaRepository<PlayerPoolRow, Integer> {
    @Query("select (count(p) > 0) from PlayerPoolRow p where p.pool.poolId = :poolId and p.player.playerId = :playerId")
    boolean existsPlayerInPool(@Param("poolId") Integer poolId, @Param("playerId") Integer playerId);

    @Modifying
    @Query("delete from PlayerPoolRow p where p.pool.poolId = :poolId and p.player.playerId = :playerId")
    void deletePlayerInPool(@Param("poolId") Integer poolId, @Param("playerId") Integer playerId);
}
