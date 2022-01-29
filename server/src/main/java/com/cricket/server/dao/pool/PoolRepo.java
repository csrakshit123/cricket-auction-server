package com.cricket.server.dao.pool;

import com.cricket.server.model.pool.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoolRepo extends JpaRepository<Pool, Integer> {
    @Query("select (count(p) > 0) from Pool p where p.poolId = :poolId and p.auction.auctionId = :auctionID")
    boolean existsPoolInAuction(@Param("poolId") Integer poolId, @Param("auctionID") Integer auctionID);

    @Query("select p from Pool p where p.auction.auctionId = :auctionId")
    List<Pool> findPoolsInAuction(@Param("auctionId") Integer auctionId);

    @Query("select p from Pool p where p.poolId = :poolId and p.auction.auctionId = :auctionId")
    Optional<Pool> findPoolInAuction(@Param("poolId") Integer poolId, @Param("auctionId") Integer auctionId);

}
