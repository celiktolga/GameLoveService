package com.game.rest;

import com.game.exceptions.PlayerNotFoundException;
import com.game.payload.LoveGameRequest;
import com.game.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestParam final String playerName) {
        playerService.createPlayer(playerName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@RequestParam(name = "id") Long id) throws PlayerNotFoundException {
        return new ResponseEntity<>(playerService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPlayer() throws PlayerNotFoundException {
        return new ResponseEntity<>(playerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/loveGame")
    public ResponseEntity<?> loveGame(@RequestBody final LoveGameRequest request) {
        playerService.request(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unLoveGame")
    public ResponseEntity<?> unLoveGame(@RequestBody final LoveGameRequest request) {
        playerService.unLove(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/lovedGames")
    public ResponseEntity<?> lovedGames() {
        return new ResponseEntity<>(playerService.lovedGames(), HttpStatus.OK);
    }

    @GetMapping("/lovedGamesByPlayerId/{playerId}")
    public ResponseEntity<?> getLovedGamesByPlayerId(@RequestParam(name = "playerId") Long playerId) {
        return new ResponseEntity<>(playerService.getLovedGamesByPlayerId(playerId), HttpStatus.OK);
    }
    @GetMapping("/mostLovedGames")
    public ResponseEntity<?> getMostLovedGames() {
        return new ResponseEntity<>(playerService.getMostLovedGames(), HttpStatus.OK);
    }
}
