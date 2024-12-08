package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Role;
import edu.redwoods.cis18.assemble.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role createRole(String roleName) {
        // Check if the role already exists
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role already exists");
        }

        // Create and save the new role
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);


    }
}
