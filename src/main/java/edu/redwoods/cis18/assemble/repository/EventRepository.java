package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
