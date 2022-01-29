package com.cricket.server.dao.auction;

import com.cricket.server.model.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Integer> {

}
