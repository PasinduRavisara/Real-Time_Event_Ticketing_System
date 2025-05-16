package com.ticketsystem.model; // Define the package for the model class

import lombok.AllArgsConstructor; // Import Lombok annotation for generating all-arguments constructor
import lombok.Data; // Import Lombok annotation for generating getters, setters, and other utility methods

@Data // Lombok annotation to generate getters, setters, equals, hash, and toString methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
public class Attendee {
    private int attendeeId; // Declare a private field for the attendee ID
}
