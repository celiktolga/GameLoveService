package com.game.service.impl;

import com.game.dto.GameDTO;
import com.game.exceptions.GameNotFoundException;
import com.game.model.Game;
import com.game.repository.GameRepository;
import com.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public GameDTO createGame(GameDTO dto) {
        Game savedGame = gameRepository.save(Game.toModel(dto));
        return Game.toDTO(savedGame);
    }

    @Override
    public GameDTO findById(Long id) throws GameNotFoundException {
        return gameRepository.findById(id)
                .map(Game::toDTO)
                .orElseThrow(() -> new GameNotFoundException(String.format("Game with id %s was not found", id)));

    }
}
