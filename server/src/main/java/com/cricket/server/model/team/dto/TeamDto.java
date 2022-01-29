package com.cricket.server.model.team.dto;

import com.cricket.server.model.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private Integer teamId;
    private String name;
    private double remainingPurse;
    private List<PlayerDto> players;
}
