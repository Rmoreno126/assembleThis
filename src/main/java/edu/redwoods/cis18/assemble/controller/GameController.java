package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }
}
