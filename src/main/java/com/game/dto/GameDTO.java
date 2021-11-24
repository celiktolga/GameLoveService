package com.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDTO {
    private String gameName;
    private Long id;

    public GameDTO(String gameName) {
        this.gameName = gameName;
    }
}
