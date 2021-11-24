package com.game.service.impl;

import com.game.dto.GameDTO;
import com.game.dto.LovedGamesDTO;
import com.game.dto.PlayerDTO;
import com.game.exceptions.PlayerNotFoundException;
import com.game.model.Game;
import com.game.model.LovedGames;
import com.game.model.Player;
import com.game.payload.LoveGameRequest;
import com.game.repository.GameRepository;
import com.game.repository.LovedGamesRepository;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final GameRepository gameRepository;
    private final LovedGamesRepository lovedGamesRepository;

    @Override
    public Long createPlayer(String playerName) {
        Player model = new Player();
        model.setPlayerName(playerName);
        repository.save(model);
        return model.getId();
    }

    @Override
    public PlayerDTO findById(Long id) throws PlayerNotFoundException {
        return repository.findById(id).map(Player::toDTO)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("Player with id %s was not found", id)));
    }

    @Override
    public List<PlayerDTO> getAll() {
        List<Player> players = repository.findAll();
        return players.stream().map(Player::toDTO).collect(toList());
    }

    @Override
    public void request(LoveGameRequest request) {
        Game game = gameRepository.getById(request.getGameId());
        Player player = repository.getById(request.getPlayerId());

        LovedGames lovedGames = new LovedGames();
        lovedGames.setGame(game);
        lovedGames.setPlayer(player);
        lovedGamesRepository.save(lovedGames);
    }

    @Override
    public List<LovedGamesDTO> lovedGames() {
        List<LovedGames> lovedGames = lovedGamesRepository.findAll();
        List<LovedGamesDTO> dtoList = new ArrayList<>();
        lovedGames.forEach(lg -> {
            LovedGamesDTO lovedGamesDTO = new LovedGamesDTO();
            lovedGamesDTO.setGameDTO(Game.toDTO(lg.getGame()));
            lovedGamesDTO.setPlayerDTO(Player.toDTO(lg.getPlayer()));

            dtoList.add(lovedGamesDTO);
        });
        return dtoList;
    }

    @Override
    public void unLove(LoveGameRequest request) {
        Game game = gameRepository.getById(request.getGameId());
        Player player = repository.getById(request.getPlayerId());

        List<LovedGames> lovedGames = lovedGamesRepository.findAllByGameAndPlayer(game, player);
        lovedGamesRepository.deleteAll(lovedGames);
    }

    @Override
    public List<GameDTO> getLovedGamesByPlayerId(Long playerId) {
        Player player = repository.getById(playerId);
        List<LovedGames> lovedGames = lovedGamesRepository.findAllByPlayer(player);
        List<GameDTO> dtoList = new ArrayList<>();
        lovedGames.forEach(lg -> {
            dtoList.add(Game.toDTO(lg.getGame()));
        });
        return dtoList;
    }

    @Override
    public GameDTO getMostLovedGames() {
        List<LovedGames> lovedGames = lovedGamesRepository.findAll();
        Map<Game, List<LovedGames>> lovedMap = lovedGames.stream()
                .collect((groupingBy(LovedGames::getGame, toList())));

        var ref = new Object() {
            int count = 0;
        };
        AtomicReference<Game> game2 = null;
        lovedMap.forEach((game, lovedGames1) -> {
            if (ref.count <= lovedGames1.size()) {
                ref.count = lovedGames1.size();
                game2.set(game);
            }
        });

        return Game.toDTO(game2.get());
    }

}
