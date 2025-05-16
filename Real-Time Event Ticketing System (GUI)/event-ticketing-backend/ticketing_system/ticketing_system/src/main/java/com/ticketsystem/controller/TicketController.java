package com.ticketsystem.controller; // Define the package for the controller class

import com.ticketsystem.model.EventTicket; // Import EventTicket model class
import com.ticketsystem.service.TicketQueueService; // Import TicketQueueService for service layer operations
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation for dependency injection
import org.springframework.web.bind.annotation.*; // Import annotations for RESTful web services

@RestController // Indicate that this class is a REST controller
@RequestMapping("/api/tickets") // Map HTTP requests to /api/tickets
public class TicketController {
    @Autowired // Automatically inject the TicketQueueService dependency
    private TicketQueueService ticketQueueService; // Declare the ticket queue service

    @PostMapping("/new") // Map POST requests to /new for creating new tickets
    public String createTicket(@RequestBody EventTicket ticket) { // Define method to handle ticket creation
        return ticketQueueService.addTicket(ticket) // Call the service to add the ticket
                ? "Ticket created successfully" // Return success message if the ticket was added
                : "Queue is at capacity"; // Return error message if the queue is full
    }

    @PostMapping("/buy") // Map POST requests to /buy for buying tickets
    public EventTicket buyTicket() { // Define method to handle ticket purchase
        return ticketQueueService.processTicketPurchase(); // Call the service to process the ticket purchase and return the ticket
    }

    @GetMapping("/available") // Map GET requests to /available for checking ticket availability
    public int checkAvailability() { // Define method to check the availability of tickets
        return ticketQueueService.getAvailableTickets(); // Call the service to get the number of available tickets and return the count
    }
}
