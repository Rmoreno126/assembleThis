package edu.redwoods.cis18.assemble.controller;

// Will contain the controllers to handle web requests

import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssembleController {

    @GetMapping("/games")
    public List<Game> getGames() {
        // Fetch game data from the database or service
        GameService gameService = null;
        return gameService.getAllGames();
    }

    @GetMapping("/store-items")
    public List<StoreItem> getStoreItems() {
        // Fetch store items data from the database or service
        return storeService.getAllStoreItems();
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        // Fetch events data from the database or service
        return eventService.getAllEvents();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerForEvent(@RequestBody RegistrationForm form) {
        // Process registration
        registrationService.register(form);
        return ResponseEntity.ok("Registration successful");
    }
}

