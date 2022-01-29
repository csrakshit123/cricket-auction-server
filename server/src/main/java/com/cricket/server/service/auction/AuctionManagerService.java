package com.cricket.server.service.auction;

import com.cricket.server.dao.auction.AuctionManagerRepo;
import com.cricket.server.dao.auction.AuctionRepo;
import com.cricket.server.dao.user.UserRepo;
import com.cricket.server.exceptions.AuctionDoesNotExistsException;
import com.cricket.server.exceptions.UserDoesNotExistsException;
import com.cricket.server.mappers.AuctionMapper;
import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.auction.AuctionManager;
import com.cricket.server.model.auction.dto.AuctionManagerDto;
import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.user.User;
import com.cricket.server.model.user.UserRole;
import com.cricket.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuctionManagerService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuctionRepo auctionRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionManagerRepo auctionManagerRepo;
    @Autowired
    private AuctionMapper auctionMapper;


    public void addManager(String managerUsername, Integer auctionId) {
        User manager = userRepo.getById(managerUsername);
        Auction auction = auctionRepo.getById(auctionId);
        AuctionManager auctionManager = new AuctionManager();
        auctionManager.setManager(manager);
        auctionManager.setAuction(auction);
        auctionManagerRepo.save(auctionManager);
    }
    public void addManager(AuctionManagerDto auctionManagerDto) throws UserDoesNotExistsException, AuctionDoesNotExistsException {
        String managerUsername = auctionManagerDto.getManagerUsername();
        Integer auctionId = auctionManagerDto.getAuctionId();
        if (!userService.checkExistingUserWithRole(managerUsername, UserRole.ADMIN)){
            throw new UserDoesNotExistsException("Invalid manager username.");
        }
        if (!auctionRepo.existsById(auctionId)){
            throw new AuctionDoesNotExistsException("Invalid Auction id.");
        }
        User manager = userRepo.getById(managerUsername);
        Auction auction = auctionRepo.getById(auctionId);
        AuctionManager auctionManager = new AuctionManager();
        auctionManager.setManager(manager);
        auctionManager.setAuction(auction);

        auctionManagerRepo.save(auctionManager);
    }

    public List<AuctionSummaryDto> getAuctionByManager(String managerUsername) {
        List<AuctionManager> auctionsByManager = auctionManagerRepo.findByManager(managerUsername);
        List<Auction> auctions = auctionsByManager.stream().map(AuctionManager::getAuction).collect(Collectors.toList());
        return auctionMapper.entityToDtoList(auctions);
    }
}
