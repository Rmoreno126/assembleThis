package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.OperatingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatingHoursRepository extends JpaRepository<OperatingHours, Long> {
}
