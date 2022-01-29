package com.cricket.server.model.player.dto;

import com.cricket.server.model.player.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Integer playerId;

    private String name;

    private PlayerType playerType;

    private String nationality;
}
