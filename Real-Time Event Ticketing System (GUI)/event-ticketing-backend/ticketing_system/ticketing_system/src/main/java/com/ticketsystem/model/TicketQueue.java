package com.ticketsystem.model; // Define the package for the model class

import lombok.Data; // Import Lombok annotation for generating boilerplate code
import java.util.LinkedList; // Import LinkedList class for queue implementation
import java.util.Queue; // Import Queue interface

@Data // Lombok annotation to generate getters, setters, equals, hash, and toString methods
public class TicketQueue {
    private final Queue<EventTicket> availableTickets = new LinkedList<>(); // Initialize the ticket queue as a LinkedList
    private final int queueLimit; // Declare a private final field for the queue limit

    // Constructor to initialize the ticket queue with a limit
    public TicketQueue(int queueLimit) {
        this.queueLimit = queueLimit; // Set the queue limit
    }

    // Synchronized method to add a ticket to the queue
    public synchronized boolean enqueueTicket(EventTicket ticket) {
        if (availableTickets.size() >= queueLimit) { // Check if the queue is full
            return false; // Return false if the queue is at capacity
        }
        availableTickets.add(ticket); // Add the ticket to the queue
        return true; // Return true if the ticket was successfully added
    }

    // Synchronized method to remove a ticket from the queue
    public synchronized EventTicket dequeueTicket() {
        return availableTickets.poll(); // Remove and return a ticket from the front of the queue
    }

    // Synchronized method to get the available space in the queue
    public synchronized int getAvailableSpace() {
        return queueLimit - availableTickets.size(); // Return the number of available spaces in the queue
    }
}
