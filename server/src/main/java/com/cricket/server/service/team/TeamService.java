package com.cricket.server.service.team;

import com.cricket.server.dao.team.TeamRepo;
import com.cricket.server.exceptions.TeamDoesNotExistsException;
import com.cricket.server.mappers.TeamMapper;
import com.cricket.server.model.team.Team;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import com.cricket.server.model.team.dto.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<TeamSummaryDto> findAllTeamsInAuction(Integer auctionId) {
        List<Team> teamsInAuction = teamRepo.findAllTeamsInAuction(auctionId);
        return teamMapper.entityToDtoList(teamsInAuction);
    }

    @Override
    public TeamDto findTeamInAuction(Integer auctionId, Integer teamId) throws TeamDoesNotExistsException {
        Team teamFound = teamRepo.findTeamInAuction(auctionId, teamId)
                .orElseThrow(() -> new TeamDoesNotExistsException("Team does not exists in this auction"));
        return teamMapper.entityToDto(teamFound);
    }
}
