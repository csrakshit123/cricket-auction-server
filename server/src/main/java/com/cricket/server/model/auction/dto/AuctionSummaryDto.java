package com.cricket.server.model.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionSummaryDto {
    private Integer auctionId;
    private String name;
    private Date auctionDate;
    private Time startTime;
}
