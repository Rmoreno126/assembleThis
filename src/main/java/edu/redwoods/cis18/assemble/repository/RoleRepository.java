package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
