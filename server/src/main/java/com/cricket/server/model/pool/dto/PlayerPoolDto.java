package com.cricket.server.model.pool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPoolDto {
    Integer poolID;
    Integer playerID;
    Double basePrice;
}
