package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Event;
import edu.redwoods.cis18.assemble.repository.EventRepository;  // Make sure to import the EventRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Role: Service Layer

Purpose: Contains business logic related to Event objects.
It acts as an intermediary between the repository layer (which interacts with the database)
and the controller layer (which handles HTTP requests).
___

Details:

Annotated with @Service, indicating it's a Spring service component.

Uses dependency injection (@Autowired) to access the EventRepository for database operations.

Provides methods for creating, retrieving, updating, and deleting Event objects.

Handles any business rules and logic needed before interacting with the database or returning data to the client.
*/

// EventService.java focuses on the business logic and operations that manipulate Event entities.

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Get all events from the repository
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get an event by its ID
    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    // Update an event's details
    public Event updateEvent(Integer id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        return eventRepository.save(event);
    }

    // Delete an event
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}
