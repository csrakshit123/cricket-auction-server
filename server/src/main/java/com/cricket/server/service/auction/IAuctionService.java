package com.cricket.server.service.auction;

import com.cricket.server.exceptions.UserDoesNotExistsException;
import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.auction.dto.AuctionDto;
import com.cricket.server.model.auction.dto.AuctionPoolDto;
import com.cricket.server.model.auction.dto.AuctionTeamDto;
import com.cricket.server.exceptions.AuctionDoesNotExistsException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.model.auction.dto.CreateAuctionDto;
import com.cricket.server.model.pool.dto.PoolDto;
import com.cricket.server.model.team.dto.TeamDto;

import java.util.List;

public interface IAuctionService {
    List<AuctionSummaryDto> findAll();

    AuctionDto findById(Integer auctionId)
            throws AuctionDoesNotExistsException;

    Integer create(CreateAuctionDto auctionDto, String managerUsername) throws UserDoesNotExistsException;

    AuctionDto update(AuctionDto auction)
            throws AuctionDoesNotExistsException;

    Integer deleteById(Integer auctionId)
            throws AuctionDoesNotExistsException;

    TeamDto addTeamToAuction(AuctionTeamDto teamToAdd) throws AuctionDoesNotExistsException;

    void removeTeamFromAuction(AuctionTeamDto teamToRemove) throws TeamDoesNotExistsException;

    PoolDto addPoolToAuction(AuctionPoolDto poolToAdd) throws AuctionDoesNotExistsException;

    void removePoolFromAuction(AuctionPoolDto poolToRemove) throws PoolDoesNotExistsException;

}
