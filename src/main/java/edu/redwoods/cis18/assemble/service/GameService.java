package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.repository.BusinessRepository;
import edu.redwoods.cis18.assemble.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    //@Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // New saveOrUpdateGame method that will not overwrite empty fields
    public Game saveOrUpdateGame(Game game) {
        if (game.getId() != null) {
            Optional<Game> existingGameOpt = gameRepository.findById(game.getId());
            if (existingGameOpt.isPresent()) {
                Game existingGame = existingGameOpt.get();
                if (game.getName() != null && !game.getName().isEmpty()) {
                    existingGame.setName(game.getName());
                }
                if (game.getType() != null && !game.getType().isEmpty()) {
                    existingGame.setType(game.getType());
                }
                if (game.getDescription() != null && !game.getDescription().isEmpty()) {
                    existingGame.setDescription(game.getDescription());
                }
                if (game.getImageUrl() != null && !game.getImageUrl().isEmpty()) {
                    existingGame.setImageUrl(game.getImageUrl());
                }
                if (game.getLogoUrl() != null && !game.getLogoUrl().isEmpty()) {
                    existingGame.setLogoUrl(game.getLogoUrl());
                }
                return gameRepository.save(existingGame);
            }
        }
        return gameRepository.save(game);
    }

    public List<Game> findGameByName(String name) {
        List<Game> games = gameRepository.findAllByName(name);
        if (games.isEmpty()) {
            throw new RuntimeException("No games found with name: " + name);
        }
        return games;
    }


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public void deleteGame(Long id) {
        
        gameRepository.deleteById(id);
    }
    
    public List<Game> getAllofTheGames() {
        return gameRepository.findAll();
    }


    public List<Game> findGamesByType(String type) {
        List<Game> games = gameRepository.findAllByType(type);
        if (games.isEmpty()) {
            throw new RuntimeException("No games found with type: " + type);
        } return games;
    }
}
