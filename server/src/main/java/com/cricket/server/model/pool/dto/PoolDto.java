package com.cricket.server.model.pool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoolDto {
    private Integer poolId;
    private String name;
    private List<PlayerPoolRowDto> playerPool;
}
