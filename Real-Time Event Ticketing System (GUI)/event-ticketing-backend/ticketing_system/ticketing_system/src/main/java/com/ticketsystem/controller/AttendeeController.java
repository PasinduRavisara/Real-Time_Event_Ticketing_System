package com.ticketsystem.controller; // Define the package for the controller class

import com.ticketsystem.service.AttendeeService; // Import AttendeeService for attendee-related operations
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation for dependency injection
import org.springframework.web.bind.annotation.*; // Import annotations for RESTful web services

@RestController // Indicate that this class is a REST controller
@RequestMapping("/api/attendees") // Map HTTP requests to /api/attendees
public class AttendeeController {
    @Autowired // Automatically inject the AttendeeService dependency
    private AttendeeService attendeeService; // Declare the attendee service

    @PostMapping("/register") // Map POST requests to /register
    public String registerAttendee(@RequestParam int attendeeId) { // Define method to handle attendee registration
        attendeeService.registerAttendee(attendeeId); // Call the service to register the attendee
        return "Attendee registration successful"; // Return a success message
    }
}
