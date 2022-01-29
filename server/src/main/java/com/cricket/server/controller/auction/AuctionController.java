package com.cricket.server.controller.auction;

import com.cricket.server.exceptions.UserDoesNotExistsException;
import com.cricket.server.model.auction.dto.AuctionDto;
import com.cricket.server.model.auction.dto.AuctionPoolDto;
import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.auction.dto.AuctionTeamDto;
import com.cricket.server.exceptions.AuctionDoesNotExistsException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.model.auction.dto.CreateAuctionDto;
import com.cricket.server.model.pool.dto.PoolDto;
import com.cricket.server.model.team.dto.TeamDto;
import com.cricket.server.service.auction.AuctionManagerService;
import com.cricket.server.service.auction.IAuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequestMapping("/api/auction")
@SecurityRequirement(name = "bearerAuth")
@RestController()
public class AuctionController {
    @Autowired
    IAuctionService auctionService;

    @Autowired
    AuctionManagerService auctionManagerService;

    @Operation(summary = "Get all auctions.")
    @GetMapping("")
    public ResponseEntity<?> getAllAuctions(){
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.findAll());
    }

    @Operation(summary = "Get auction by id.")
    @GetMapping("/byId/{auctionId}")
    public ResponseEntity<?> getAuctionById(@PathVariable Integer auctionId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(auctionService.findById(auctionId));
        } catch (AuctionDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Get auctions by manager.")
    @GetMapping("/byManager/{managerUsername}")
    public ResponseEntity<?> getAuctionsByManager(@PathVariable(value = "managerUsername") String managerUsername){
        List<AuctionSummaryDto> auctionByManager =  auctionManagerService.getAuctionByManager(managerUsername);
        return ResponseEntity.ok(auctionByManager);
    }

    @Operation(summary = "Create a new auction.")
    @PostMapping("/{managerUsername}")
    public ResponseEntity<?> createAuction(@RequestBody CreateAuctionDto auctionDto,
                                           @PathVariable("managerUsername") String managerUsername){
        Integer auctionId = null;
        try {
            auctionId = auctionService.create(auctionDto, managerUsername);
        } catch (UserDoesNotExistsException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(auctionId);
    }

    @Operation(summary = "Add new team to auction.")
    @PutMapping("/addTeam")
    public ResponseEntity<?> addTeamToAuction(@RequestBody AuctionTeamDto teamToAdd){
        try {
            TeamDto teamAdded = auctionService.addTeamToAuction(teamToAdd);
            return ResponseEntity.status(HttpStatus.OK).body(teamAdded);
        } catch (AuctionDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove a team from auction.")
    @PutMapping("/removeTeam")
    public ResponseEntity<?> removeTeamFromAuction(@RequestBody AuctionTeamDto teamToRemove){
        try {
            auctionService.removeTeamFromAuction(teamToRemove);
            return ResponseEntity.status(HttpStatus.OK).body("Team removed from auction.");
        } catch (TeamDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Add a new pool to the auction.")
    @PutMapping("/addPool")
    public ResponseEntity<?> addPoolToAuction(@RequestBody AuctionPoolDto poolToAdd){
        try {
            PoolDto poolAdded = auctionService.addPoolToAuction(poolToAdd);
            return ResponseEntity.status(HttpStatus.OK).body(poolAdded);
        } catch (AuctionDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove a pool from the auction")
    @PutMapping("/removePool")
    public ResponseEntity<?> removePoolFromAuction(@RequestBody AuctionPoolDto poolToRemove){
        try {
            auctionService.removePoolFromAuction(poolToRemove);
            return ResponseEntity.status(HttpStatus.OK).body("Pool removed from auction");
        } catch (PoolDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
