package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.User;
import edu.redwoods.cis18.assemble.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller // This means that this class is a Controller
@RequestMapping(path="/user") // This means URLs start with /user (after Application path)
public class UserController {
    // Autowired will connect the userRepository variable to a bean dubbed userRepository,
    // which is auto-generated by SpringBoot JPA. We will use it to perform CRUD operations on the user data.
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests. POST == CREATE in CRUD.
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all") // Map ONLY GET Requests. GET == READ in CRUD.
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
    // A GET Request that utilizes the findByEmail method signature we auto-wired in our UserRepository
    @GetMapping(path="/email")
    public @ResponseBody Iterable<User> getUsersByEmail(@RequestParam String email) {
        // This returns a JSON or XML with the users
        return userRepository.findByEmail(email);
    }


    }