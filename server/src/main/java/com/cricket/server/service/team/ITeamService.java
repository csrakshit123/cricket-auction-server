package com.cricket.server.service.team;

import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import com.cricket.server.model.team.dto.TeamDto;

import java.util.List;

public interface ITeamService {
    List<TeamSummaryDto> findAllTeamsInAuction(Integer auctionId);

    TeamDto findTeamInAuction(Integer auctionId, Integer teamId) throws TeamDoesNotExistsException;
}
