package com.cricket.server.model.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionTeamDto {
    private Integer auctionId;
    private Integer teamId;
    private String teamName;
}
