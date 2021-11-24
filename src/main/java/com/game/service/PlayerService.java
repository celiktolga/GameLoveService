package com.game.service;

import com.game.dto.GameDTO;
import com.game.dto.LovedGamesDTO;
import com.game.dto.PlayerDTO;
import com.game.exceptions.GameNotFoundException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.model.LovedGames;
import com.game.payload.LoveGameRequest;
import com.game.payload.PlayerRequest;

import java.util.List;

public interface PlayerService {

    Long createPlayer(String playerName);

    PlayerDTO findById(Long id) throws PlayerNotFoundException;

    List<PlayerDTO> getAll();

    void request(LoveGameRequest request);

    List<LovedGamesDTO> lovedGames();

    void unLove(LoveGameRequest request);

    List<GameDTO> getLovedGamesByPlayerId(Long playerId);

    GameDTO getMostLovedGames();
}
