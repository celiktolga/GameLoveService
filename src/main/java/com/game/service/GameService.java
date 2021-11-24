package com.game.service;

import com.game.dto.GameDTO;
import com.game.exceptions.GameNotFoundException;

public interface GameService {
    GameDTO createGame(GameDTO dto);

    GameDTO findById(Long id) throws GameNotFoundException;
}
