package com.ticketsystem.controller; // Define the package for the controller class

import com.ticketsystem.model.EventOrganizer; // Import EventOrganizer model class
import com.ticketsystem.service.EventOrganizerService; // Import EventOrganizerService for service layer operations
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation for dependency injection
import org.springframework.web.bind.annotation.*; // Import annotations for RESTful web services
import java.util.List; // Import List class for handling collections of organizers

@RestController // Indicate that this class is a REST controller
@RequestMapping("/api/organizers") // Map HTTP requests to /api/organizers
public class EventOrganizerController {
    @Autowired // Automatically inject the EventOrganizerService dependency
    private EventOrganizerService organizerService; // Declare the organizer service

    @PostMapping // Map POST requests to the base URL
    public String registerOrganizer(@RequestBody EventOrganizer organizer) { // Define method to handle organizer registration
        organizerService.registerOrganizer(organizer); // Call the service to register the organizer
        return "Organizer registered successfully"; // Return a success message
    }

    @GetMapping // Map GET requests to the base URL
    public List<EventOrganizer> getAllOrganizers() { // Define method to retrieve all organizers
        return organizerService.getAllOrganizers(); // Call the service to get all organizers and return the list
    }

    @GetMapping("/{id}") // Map GET requests to /{id}
    public EventOrganizer getOrganizerDetails(@PathVariable int id) { // Define method to retrieve organizer details by ID
        return organizerService.findOrganizerById(id); // Call the service to find the organizer by ID and return the details
    }
}
