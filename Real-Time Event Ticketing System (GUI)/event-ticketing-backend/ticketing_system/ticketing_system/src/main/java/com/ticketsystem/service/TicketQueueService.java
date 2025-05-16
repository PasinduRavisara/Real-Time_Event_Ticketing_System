package com.ticketsystem.service; // Define the package for the service class

import com.ticketsystem.model.EventTicket; // Import EventTicket model class
import com.ticketsystem.model.TicketQueue; // Import TicketQueue model class
import org.springframework.stereotype.Service; // Import Service annotation for Spring service

@Service // Indicate that this class is a Spring service
public class TicketQueueService {
    private final TicketQueue ticketQueue = new TicketQueue(50); // Initialize a ticket queue with a limit of 50 tickets

    // Method to add a ticket to the queue
    public boolean addTicket(EventTicket ticket) {
        return ticketQueue.enqueueTicket(ticket); // Return true if the ticket is successfully added, otherwise false
    }

    // Method to process a ticket purchase by removing a ticket from the queue
    public EventTicket processTicketPurchase() {
        return ticketQueue.dequeueTicket(); // Remove and return a ticket from the queue
    }

    // Method to get the number of available spaces in the ticket queue
    public int getAvailableTickets() {
        return ticketQueue.getAvailableSpace(); // Return the number of available spaces in the queue
    }
}
