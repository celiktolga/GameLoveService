package com.game.payload;

import com.game.dto.GameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    private Long id;
    private String name;

    public GameResponse(GameDTO gameDTO) {
        this.name = gameDTO.getGameName();
        this.id = gameDTO.getId();
    }
}
