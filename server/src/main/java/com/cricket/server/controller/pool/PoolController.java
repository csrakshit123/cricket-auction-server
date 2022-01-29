package com.cricket.server.controller.pool;

import com.cricket.server.model.pool.dto.PlayerPoolDto;
import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.exceptions.PlayerDoesNotExistsInPoolException;
import com.cricket.server.exceptions.PoolDoesNotExistsException;
import com.cricket.server.model.pool.dto.PlayerPoolRowDto;
import com.cricket.server.service.pool.IPoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/pool")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class PoolController {
    @Autowired
    IPoolService poolService;

    @Operation(summary = "Get all pools under this auction.")
    @GetMapping("/{auctionId}")
    public ResponseEntity<?> getAllPools(@PathVariable Integer auctionId){
        return ResponseEntity.status(HttpStatus.OK).body(poolService.findAllPoolsInAuction(auctionId));
    }

    @Operation(summary = "Get Pool by id in this auction.")
    @GetMapping("/{auctionId}/{poolId}")
    public ResponseEntity<?> getPoolInAuction(@PathVariable(value = "auctionId") Integer auctionId, @PathVariable(value = "poolId") Integer poolId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(poolService.findPoolInAuction(auctionId, poolId));
        } catch (PoolDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Add player to this pool.")
    @PutMapping("/addPlayerToPool")
    public ResponseEntity<?> addPlayerToPool(@RequestBody PlayerPoolDto playerToAdd){
        try {
            PlayerPoolRowDto playerRowAdded = poolService.addPlayerToPool(playerToAdd);
            return ResponseEntity.status(HttpStatus.OK).body(playerRowAdded);
        } catch (PoolDoesNotExistsException | PlayerDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove player from this pool.")
    @PutMapping("/removePlayerFromPool")
    public ResponseEntity<?> removePlayerFromPool(@RequestBody PlayerPoolDto playerToRemove){
        try {
            poolService.removePlayerFromPool(playerToRemove);
            return ResponseEntity.status(HttpStatus.OK).body("Removed player from the pool.");
        } catch (PlayerDoesNotExistsInPoolException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
