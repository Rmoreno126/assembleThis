package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
