package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    // Find businesses by location (case-insensitive search)
    @Query("SELECT b FROM Business b WHERE LOWER(b.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Business> findByLocation(@Param("location") String location);

    // Find businesses by name (already done)
    @Query("SELECT b FROM Business b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Business> findAllByName(@Param("name") String name);

    // Find businesses by category (case-insensitive search)
    @Query("SELECT b FROM Business b WHERE LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))")
    List<Business> findByCategory(@Param("category") String category);

    // Find a business by name (case-insensitive search)
    @Query("SELECT b FROM Business b WHERE LOWER(b.name) = LOWER(:name)")
    Optional<Business> findByName(@Param("name") String name);

    // Implementing Pagination which will let us control how many business objects are returned at a time
    Page<Business> findAll(Pageable pageable);
}
