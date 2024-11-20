package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.RegistrationForm;
import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.model.Event;
import edu.redwoods.cis18.assemble.model.Game;
import edu.redwoods.cis18.assemble.service.GameService;
import edu.redwoods.cis18.assemble.service.StoreService;  // Import the StoreService
import edu.redwoods.cis18.assemble.service.EventService;  // Ensure EventService is imported if required
import edu.redwoods.cis18.assemble.service.RegistrationService;  // Ensure RegistrationService is imported if required
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssembleController {

    @Autowired
    private GameService gameService;  // Injecting GameService

    @Autowired
    private StoreService storeService;  // Injecting StoreService to get business data

    @Autowired
    private EventService eventService;  // Injecting EventService (make sure it exists)

    @Autowired
    private RegistrationService registrationService;  // Injecting RegistrationService (make sure it exists)

    @GetMapping("/games")
    public List<Game> getGames() {
        // Fetch game data from the service
        return gameService.getAllGames();
    }

    @GetMapping("/stores")
    public List<Business> getStores() {
        // Fetch store/business data from the service
        return storeService.getAllBusinesses();  // This will now work because storeService is injected
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        // Fetch events data from the service
        return eventService.getAllEvents();  // Works with the EventService to retrieve all events
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerForEvent(@RequestBody RegistrationForm form) {
        // Process registration
        registrationService.register(form);
        return ResponseEntity.ok("Registration successful");
    }
}
