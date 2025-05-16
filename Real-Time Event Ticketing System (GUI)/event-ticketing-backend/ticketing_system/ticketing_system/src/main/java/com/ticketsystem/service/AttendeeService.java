package com.ticketsystem.service; // Define the package for the service class

import org.springframework.stereotype.Service; // Import Service annotation for Spring service
import java.util.HashSet; // Import HashSet class for storing unique attendee IDs
import java.util.Set; // Import Set interface

@Service // Indicate that this class is a Spring service
public class AttendeeService {
    private final Set<Integer> registeredAttendees = new HashSet<>(); // Initialize a set to store registered attendee IDs

    // Method to register an attendee by their ID
    public void registerAttendee(int attendeeId) {
        registeredAttendees.add(attendeeId); // Add the attendee ID to the set of registered attendees
    }
}
