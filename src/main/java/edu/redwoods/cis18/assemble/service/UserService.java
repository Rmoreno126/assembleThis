package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Role;
import edu.redwoods.cis18.assemble.model.User;
import edu.redwoods.cis18.assemble.repository.RoleRepository;
import edu.redwoods.cis18.assemble.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Use for password hashing
    }

    public User registerUser(User user, String roleName) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Fetch the role by name
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign the role
        user.setRoles(Set.of(role));

        // Save and return the user
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
