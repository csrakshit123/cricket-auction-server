package com.cricket.server.model.auction.dto;

import com.cricket.server.model.pool.dto.PoolDto;
import com.cricket.server.model.team.dto.TeamSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuctionDto {
    private Integer auctionId;
    private String name;
    private double maxPurse;
    private Date auctionDate;
    private Time startTime;
    private List<PoolDto> auctionPools;
    private List<TeamSummaryDto> teams;
}
