package com.cricket.server.service.auction;

import com.cricket.server.exceptions.UserDoesNotExistsException;
import com.cricket.server.mappers.PoolMapper;
import com.cricket.server.mappers.TeamMapper;
import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.auction.dto.AuctionDto;
import com.cricket.server.model.auction.dto.AuctionPoolDto;
import com.cricket.server.model.auction.dto.AuctionTeamDto;
import com.cricket.server.dao.auction.AuctionRepo;
import com.cricket.server.dao.pool.PoolRepo;
import com.cricket.server.dao.team.TeamRepo;
import com.cricket.server.exceptions.AuctionDoesNotExistsException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.mappers.AuctionMapper;
import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.auction.dto.CreateAuctionDto;
import com.cricket.server.model.pool.Pool;
import com.cricket.server.model.pool.dto.PoolDto;
import com.cricket.server.model.team.Team;
import com.cricket.server.model.team.dto.TeamDto;
import com.cricket.server.model.user.UserRole;
import com.cricket.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionRepo auctionRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private PoolRepo poolRepo;

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private PoolMapper poolMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private AuctionManagerService auctionManagerService;

    @Autowired
    private UserService userService;

    @Override
    public List<AuctionSummaryDto> findAll(){
        List<Auction> auctionList = auctionRepo.findAll();
        return auctionMapper.entityToDtoList(auctionList);
    }

    @Override
    public AuctionDto findById(Integer auctionId)
            throws AuctionDoesNotExistsException {
        Auction auction = getAuctionById(auctionId);
        return auctionMapper.entityToDto(auction);
    }

    private Auction getAuctionById(Integer auctionId) throws AuctionDoesNotExistsException {
        return auctionRepo.findById(auctionId)
                .orElseThrow(() -> new AuctionDoesNotExistsException("Auction with id: " + auctionId + " does not exists."));
    }

    @Override
    public Integer create(CreateAuctionDto auctionDto, String managerUsername) throws UserDoesNotExistsException {
        if (!userService.checkExistingUserWithRole(managerUsername, UserRole.ADMIN)){
            throw new UserDoesNotExistsException("Invalid manager username.");
        }
        Auction auctionToCreate = auctionMapper.dtoToEntity(auctionDto);
        Integer newAuctionId =  auctionRepo.save(auctionToCreate).getAuctionId();
        auctionManagerService.addManager(managerUsername, newAuctionId);
        return newAuctionId;
    }

    @Override
    public AuctionDto update(AuctionDto auctionDto)
            throws AuctionDoesNotExistsException {
        if (!auctionRepo.existsById(auctionDto.getAuctionId())){
            throw new AuctionDoesNotExistsException("Auction with id: " + auctionDto.getAuctionId() + " does not exists.");
        }
        Auction auctionToUpdate = auctionMapper.dtoToEntity(auctionDto);
        Auction updatedAuction =  auctionRepo.save(auctionToUpdate);
        return auctionMapper.entityToDto(updatedAuction);
    }

    @Override
    public Integer deleteById(Integer auctionId)
            throws AuctionDoesNotExistsException {
        if (!auctionRepo.existsById(auctionId)){
            throw new AuctionDoesNotExistsException("Auction with id: " + auctionId + " does not exists.");
        }
        auctionRepo.deleteById(auctionId);
        return auctionId;
    }

    @Override
    public TeamDto addTeamToAuction(AuctionTeamDto teamToAdd) throws AuctionDoesNotExistsException {
        Auction currentAuction = getAuctionById(teamToAdd.getAuctionId());
        Team team = new Team();
        team.setName(teamToAdd.getTeamName());
        team.setAuction(currentAuction);
        Team teamAdded =  teamRepo.save(team);
        return teamMapper.entityToDto(teamAdded);
    }

    @Override
    public void removeTeamFromAuction(AuctionTeamDto teamToRemove) throws TeamDoesNotExistsException {
        if (!teamRepo.existsTeamInAuction(teamToRemove.getTeamId(), teamToRemove.getAuctionId())) {
            throw new TeamDoesNotExistsException("Team does not exists in this auction");
        }
        teamRepo.deleteById(teamToRemove.getTeamId());
    }

    @Override
    public PoolDto addPoolToAuction(AuctionPoolDto poolToAdd) throws AuctionDoesNotExistsException {
        Auction currentAuction = getAuctionById(poolToAdd.getAuctionId());
        Pool pool = new Pool();
        pool.setName(poolToAdd.getPoolName());
        pool.setAuction(currentAuction);
        Pool poolAdded = poolRepo.save(pool);
        return poolMapper.entityToDto(poolAdded);
    }

    @Override
    public void removePoolFromAuction(AuctionPoolDto poolToRemove) throws PoolDoesNotExistsException {
        if (!poolRepo.existsPoolInAuction(poolToRemove.getPoolId(), poolToRemove.getAuctionId())){
            throw new PoolDoesNotExistsException("Pool with id: " + poolToRemove.getPoolId() + " does not exists in the auction.");
        }
        poolRepo.deleteById(poolToRemove.getPoolId());

    }


}
