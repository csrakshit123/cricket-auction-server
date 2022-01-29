package com.cricket.server.model.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionPoolDto {
    private Integer auctionId;
    private Integer poolId;
    private String poolName;
}
