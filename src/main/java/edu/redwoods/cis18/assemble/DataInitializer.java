/*
package edu.redwoods.cis18.assemble;

import edu.redwoods.cis18.assemble.model.*;
import edu.redwoods.cis18.assemble.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final GameRepository gameRepository;
    private final EventRepository eventRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BusinessRepository businessRepository,
                           GameRepository gameRepository,
                           EventRepository eventRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.gameRepository = gameRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Clear existing data
        System.out.println("Deleting all data...");
        userRepository.deleteAll();
        roleRepository.deleteAll();
        businessRepository.deleteAll();
        gameRepository.deleteAll();
        eventRepository.deleteAll();

        // Create roles
        System.out.println("Creating roles...");
        Role userRole = roleRepository.save(new Role("USER"));
        Role adminRole = roleRepository.save(new Role("ADMIN"));

        // Create users
        System.out.println("Creating users...");
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("adminpassword");
        admin.setProfilePictureUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);

        User regularUser = new User();
        regularUser.setName("Regular User");
        regularUser.setEmail("user@example.com");
        regularUser.setPassword("userpassword");
        regularUser.setProfilePictureUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
        regularUser.setRoles(Set.of(userRole));
        userRepository.save(regularUser);

        // Add 5 more users
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("Test User " + i);
            user.setEmail("testuser" + i + "@example.com");
            user.setPassword("password" + i);
            user.setProfilePictureUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }

        // Create businesses
        System.out.println("Creating businesses...");
        for (int i = 1; i <= 5; i++) {
            Business business = new Business();
            business.setName("Business " + i);
            business.setDescription("Description for Business " + i);
            business.setImageUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
            business.setLocation("123 Business Lane " + i);
            business.setCategory("Category " + i);
            business.setLatitude(34.0522 + i);
            business.setLongitude(-118.2437 - i);
            business.setOwner(admin);

            // Add operating hours
            business.getOperatingHours().add(new OperatingHours(business, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), false));
            business.getOperatingHours().add(new OperatingHours(business, DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), false));
            businessRepository.save(business);
        }

        // Create games
        System.out.println("Creating games...");
        for (int i = 1; i <= 5; i++) {
            Game game = new Game();
            game.setName("Game " + i);
            game.setType("Type " + i);
            game.setImageUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
            game.setDescription("Description for Game " + i);
            gameRepository.save(game);
        }

        // Create events
        System.out.println("Creating events...");
        for (int i = 1; i <= 5; i++) {
            Event event = new Event();
            event.setName("Event " + i);
            event.setDescription("Description for Event " + i);
            event.setDate(LocalDate.now().plusDays(i));
            event.setTime(LocalTime.of(10 + i, 0));
            event.setImageUrl("https://assets.pokemon.com/static2/_ui/img/og-default-image.jpeg");
            event.setBusiness(businessRepository.findAll().get(i - 1));
            event.setHost(admin);
            eventRepository.save(event);
        }

        System.out.println("Dummy data initialized successfully!");
    }
}
*/
