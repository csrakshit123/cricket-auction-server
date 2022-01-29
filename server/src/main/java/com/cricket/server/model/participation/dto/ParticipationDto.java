package com.cricket.server.model.participation.dto;

import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.participation.ParticipationType;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import com.cricket.server.model.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private Integer participantId;
    private UserDto user;
    private AuctionSummaryDto auction;
    private TeamSummaryDto team;
    private ParticipationType participationType;
}
