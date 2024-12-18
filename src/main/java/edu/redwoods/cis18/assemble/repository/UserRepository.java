package edu.redwoods.cis18.assemble.repository;

import edu.redwoods.cis18.assemble.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Spring Data JPA focuses on using JPA to store data in a relational database.
// Its most compelling feature is the ability to create repository implementations
// automatically, at runtime, from a repository interface.
// Below, UserRepository will be AUTO IMPLEMENTED by Spring into a Bean called
// userRepository. CRUD refers to Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {
    // Spring Data JPA lets you define other query methods simply by declaring their method signature. For example,
    // the userRepository bean includes the **findByEmail** method after this, and it gets auto-wired to our User entity
    // getEmail getter via JPA.
    //List<User> findByEmail(String email);
    Optional<User> findByEmail(String email);
}
