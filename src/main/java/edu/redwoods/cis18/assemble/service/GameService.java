package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Role: Service Layer

Purpose: Contains business logic related to Game objects.
It acts as an intermediary between the repository layer (which interacts with the database)
and the controller layer (which handles HTTP requests).
___

Details:

Annotated with @Service, indicating it's a Spring service component.

Uses dependency injection (@Autowired) to access the GameRepository for database operations.

Provides methods for creating, retrieving, updating, and deleting Game objects.

Handles any business rules and logic needed before interacting with the database or returning data to the client.
*/

// GameService.java focuses on the business logic and operations that manipulate Game entities.

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game updateGame(Integer id, Game gameDetails) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setName(gameDetails.getName());
        game.setType(gameDetails.getType());
        game.setImageUrl(gameDetails.getImageUrl());
        return gameRepository.save(game);
    }

    public void deleteGame(Integer id) {
        gameRepository.deleteById(id);
    }
}
