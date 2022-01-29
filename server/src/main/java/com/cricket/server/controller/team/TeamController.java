package com.cricket.server.controller.team;

import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import com.cricket.server.model.team.dto.TeamDto;
import com.cricket.server.service.team.ITeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/team")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @Operation(summary = "Get all teams in this auction.")
    @GetMapping("/{auctionId}")
    public ResponseEntity<?> getAllTeamsInAuction(@PathVariable(value = "auctionId") Integer auctionId){
        List<TeamSummaryDto> allTeamsInAuction = teamService.findAllTeamsInAuction(auctionId);
        return ResponseEntity.status(HttpStatus.OK).body(allTeamsInAuction);
    }

    @Operation(summary = "Get individual team details in this auction.")
    @GetMapping("/{auctionId}/{teamId}")
    public ResponseEntity<?> getTeamInAuction(@PathVariable(value = "auctionId") Integer auctionId, @PathVariable(value = "teamId") Integer teamId){
        try {
            TeamDto teamInAuction = teamService.findTeamInAuction(auctionId, teamId);
            return ResponseEntity.status(HttpStatus.OK).body(teamInAuction);
        } catch (TeamDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
