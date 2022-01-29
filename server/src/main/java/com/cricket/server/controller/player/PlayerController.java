package com.cricket.server.controller.player;

import com.cricket.server.exceptions.PlayerDoesNotExistsException;
import com.cricket.server.model.player.dto.PlayerDto;
import com.cricket.server.service.player.IPlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/player")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @Operation(summary = "Get all players.")
    @GetMapping("")
    public ResponseEntity<?> getAllPlayers(){
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @Operation(summary = "Get player by player id.")
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable(value = "playerId") Integer playerId){
        try {
            PlayerDto playerFound = playerService.findById(playerId);
            return ResponseEntity.ok().body(playerFound);
        } catch (PlayerDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Create new player.")
    @PostMapping()
    public ResponseEntity<?> addNewPlayer(@RequestBody PlayerDto playerDto){
        PlayerDto createdPlayer = playerService.create(playerDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdPlayer);
    }

    @Operation(summary = "Edit existing player.")
    @PutMapping("/edit")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerDto playerDto){
        try {
            PlayerDto updatedPlayer = playerService.update(playerDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPlayer);
        } catch (PlayerDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete player")
    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "playerId") Integer playerId){
        try {
            playerService.delete(playerId);
            return ResponseEntity.status(HttpStatus.OK).body("Player with playerId: " + playerId + " deleted");
        } catch (PlayerDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
