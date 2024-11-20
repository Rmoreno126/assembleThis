package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.RegistrationForm;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void register(RegistrationForm form) {
        // Process the registration logic
        // e.g., save to database, send confirmation email, etc.
        System.out.println("Registering: " + form.getName() + " for event ID: " + form.getEventId());
        // Add your actual registration logic here
    }
}
