package com.cricket.server.dao.auction;

import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.auction.AuctionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionManagerRepo extends JpaRepository<AuctionManager, Integer> {
    @Query("select a from AuctionManager a where a.manager.username = :managerUsername")
    List<AuctionManager> findByManager(@Param("managerUsername") String managerUsername);
}
