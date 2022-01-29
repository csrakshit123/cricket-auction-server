package com.cricket.server.model.pool.dto;

import com.cricket.server.model.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPoolRowDto {
    private Integer playerPoolId;
    private PlayerDto player;
    private double basePrice;
}
