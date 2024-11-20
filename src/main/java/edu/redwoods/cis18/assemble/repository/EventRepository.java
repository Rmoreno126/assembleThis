package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

// EventRepository extends JpaRepository, providing built-in methods for CRUD operations
public interface EventRepository extends JpaRepository<Event, Integer> {
    // Additional custom queries can be added here if needed
}
