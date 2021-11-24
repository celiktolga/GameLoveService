package com.game.payload;

import lombok.Data;

@Data
public class LoveGameRequest {
    private Long gameId;
    private Long playerId;
}
