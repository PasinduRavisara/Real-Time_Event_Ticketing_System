package com.ticketsystem.model; // Define the package for the model class

public class EventOrganizer {
    private int organizerId; // Declare a private field for the organizer ID

    // Constructor to initialize the organizer with an ID
    public EventOrganizer(int organizerId) {
        this.organizerId = organizerId; // Set the organizer ID
    }

    // Getter for organizer ID
    public int getOrganizerId() {
        return organizerId; // Return the organizer ID
    }

    // Setter for organizer ID
    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId; // Set the organizer ID
    }
}
