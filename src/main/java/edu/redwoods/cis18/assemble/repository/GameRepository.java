package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // Find game by partOfName
    @Query("SELECT g FROM Game g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Game> findAllByName(@Param("name") String name);

    // Find a game by exactName (case-insensitive search)
    @Query("SELECT g FROM Game g WHERE LOWER(g.name) = LOWER(:name)")
    Optional<Game> findByName(@Param("name") String name);

    // Find all games by type
    @Query("SELECT g FROM Game g WHERE LOWER(g.type) LIKE LOWER(CONCAT('%', :type, '%'))")
    List<Game> findAllByType(@Param("type") String type);

    // Find distinct game types
    @Query("SELECT DISTINCT g.type FROM Game g")
    List<String> findDistinctTypes();
}
