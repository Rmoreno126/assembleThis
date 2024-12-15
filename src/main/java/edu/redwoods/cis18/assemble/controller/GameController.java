package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Create or Update a Game
    @PostMapping
    public ResponseEntity<Game> saveOrUpdateGame(@RequestBody Game game) {
        Game savedGame = gameService.saveOrUpdateGame(game);
        return ResponseEntity.ok(savedGame);
    }

    // Get All Games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    // Get a Game by ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        return game.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a Game by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    // Api /all endpoint for testing
    @GetMapping("/all")
    public List<Game> getAllOfTheGames() {
        return gameService.getAllGames();
    }

    // API /name endpoint
    @GetMapping("/name")
    public List<Game> getGameByName(@RequestParam String name) {
        return gameService.findGameByName(name);
    }

    // API /type endpoint
    @GetMapping("/type")
    public List<Game> getGamesByType(@RequestParam String type) {
        return gameService.findGamesByType(type);
    }
}
