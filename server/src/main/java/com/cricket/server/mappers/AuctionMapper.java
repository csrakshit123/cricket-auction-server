package com.cricket.server.mappers;

import com.cricket.server.model.auction.dto.AuctionSummaryDto;
import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.auction.dto.AuctionDto;
import com.cricket.server.model.auction.dto.CreateAuctionDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", uses = {PoolMapper.class, TeamMapper.class})
public interface AuctionMapper {

    List<Auction> dtoToEntityList(List<AuctionSummaryDto> auctionSummaryDtoList);
    List<AuctionSummaryDto> entityToDtoList(List<Auction> auctionList);

    AuctionDto entityToDto(Auction auction);
    Auction dtoToEntity(AuctionDto auctionDto);
    Auction dtoToEntity(CreateAuctionDto auctionDto);
}
