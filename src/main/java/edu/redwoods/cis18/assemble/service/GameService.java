package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game saveOrUpdateGame(Game game) {
        return gameRepository.save(game);
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
}
