package com.ticketsystem; // Define the package for the main application class

import org.springframework.boot.SpringApplication; // Import SpringApplication class for bootstrapping the application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Import SpringBootApplication annotation for configuration

@SpringBootApplication // Indicate that this class is a Spring Boot application
public class EventTicketingApplication {
	// Main method to start the application
	public static void main(String[] args) {
		SpringApplication.run(EventTicketingApplication.class, args); // Bootstraps the application, starting the Spring context
		System.out.println("\nEvent Ticketing System is now operational..."); // Print a message indicating the system is operational
	}
}
