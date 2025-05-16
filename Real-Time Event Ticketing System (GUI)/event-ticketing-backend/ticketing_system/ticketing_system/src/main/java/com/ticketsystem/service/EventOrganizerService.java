package com.ticketsystem.service; // Define the package for the service class

import com.ticketsystem.model.EventOrganizer; // Import EventOrganizer model class
import org.springframework.stereotype.Service; // Import Service annotation for Spring service
import java.util.ArrayList; // Import ArrayList class for storing organizers
import java.util.List; // Import List interface

@Service // Indicate that this class is a Spring service
public class EventOrganizerService {
    private final List<EventOrganizer> organizers = new ArrayList<>(); // Initialize a list to store event organizers

    // Method to register a new organizer
    public void registerOrganizer(EventOrganizer organizer) {
        organizers.add(organizer); // Add the organizer to the list
    }

    // Method to get all registered organizers
    public List<EventOrganizer> getAllOrganizers() {
        return organizers; // Return the list of all organizers
    }

    // Method to find an organizer by their ID
    public EventOrganizer findOrganizerById(int organizerId) {
        return organizers.stream() // Stream through the list of organizers
                .filter(o -> o.getOrganizerId() == organizerId) // Filter organizers by matching ID
                .findFirst() // Get the first matching organizer
                .orElse(null); // Return null if no organizer is found with the given ID
    }
}
