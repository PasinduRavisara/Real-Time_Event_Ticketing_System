package com.ticketsystem.model; // Define the package for the model class

import lombok.Data; // Import Lombok annotation for generating boilerplate code

@Data // Lombok annotation to generate getters, setters, equals, hash, and toString methods
public class SystemSettingsRequest {
    private int totalTickets; // Declare a private field for total tickets
    private int ticketReleaseRate; // Declare a private field for ticket release rate
    private int customerRetrievalRate; // Declare a private field for customer retrieval rate
    private int maxTicketCapacity; // Declare a private field for maximum ticket capacity

    // Getter for total tickets
    public int getTotalTickets() {
        return totalTickets; // Return the total tickets
    }

    // Getter for ticket release rate
    public int getTicketReleaseRate() {
        return ticketReleaseRate; // Return the ticket release rate
    }

    // Getter for customer retrieval rate
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate; // Return the customer retrieval rate
    }

    // Getter for maximum ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity; // Return the maximum ticket capacity
    }
}
