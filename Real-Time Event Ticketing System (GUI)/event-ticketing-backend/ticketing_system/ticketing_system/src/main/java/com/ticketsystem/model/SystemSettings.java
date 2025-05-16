package com.ticketsystem.model; // Define the package for the model class

import com.fasterxml.jackson.annotation.JsonProperty; // Import JsonProperty annotation for JSON property mapping
import lombok.*; // Import Lombok annotations for generating boilerplate code

@Data // Lombok annotation to generate getters, setters, equals, hash, and toString methods
@Getter // Lombok annotation to generate getter methods
@Setter // Lombok annotation to generate setter methods
@NoArgsConstructor // Lombok annotation to generate a no-arguments constructor
public class SystemSettings {
    @JsonProperty("totalTickets") // Map the totalTickets field to the JSON property "totalTickets"
    private int totalTickets; // Declare a private field for total tickets

    @JsonProperty("ticketReleaseRate") // Map the ticketReleaseRate field to the JSON property "ticketReleaseRate"
    private int ticketReleaseRate; // Declare a private field for ticket release rate

    @JsonProperty("customerRetrievalRate") // Map the customerRetrievalRate field to the JSON property "customerRetrievalRate"
    private int customerRetrievalRate; // Declare a private field for customer retrieval rate

    @JsonProperty("maxTicketCapacity") // Map the maxTicketCapacity field to the JSON property "maxTicketCapacity"
    private int maxTicketCapacity; // Declare a private field for maximum ticket capacity

    // Constructor to initialize system settings with all parameters
    public SystemSettings(int totalTickets, int ticketReleaseRate,
                          int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets; // Set the total tickets
        this.ticketReleaseRate = ticketReleaseRate; // Set the ticket release rate
        this.customerRetrievalRate = customerRetrievalRate; // Set the customer retrieval rate
        this.maxTicketCapacity = maxTicketCapacity; // Set the maximum ticket capacity
    }
}
