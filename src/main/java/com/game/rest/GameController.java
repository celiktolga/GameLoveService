package com.game.rest;

import com.game.dto.GameDTO;
import com.game.payload.GameResponse;
import com.game.exceptions.GameNotFoundException;
import lombok.RequiredArgsConstructor;
import com.game.payload.GameRequest;
import com.game.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse create(@RequestBody GameRequest gameRequest) {
        return new GameResponse(gameService.createGame(new GameDTO(gameRequest.getName())));
    }

    @GetMapping("/{id}")
    public GameResponse findGameById(@PathVariable("id") Long id) throws GameNotFoundException {
        return new GameResponse(gameService.findById(id));
    }

//    @GetMapping
//    public List<GameResponse> findGamesByName(@RequestParam("name") String name) {
//        return gameService.findGamesByName(name);
//    }
}
