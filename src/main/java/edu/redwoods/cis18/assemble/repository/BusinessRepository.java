package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    // Additional custom queries can be added here if needed
}
