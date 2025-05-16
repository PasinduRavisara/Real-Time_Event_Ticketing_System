package com.ticketsystem.controller; // Define the package for the controller class

import com.ticketsystem.model.SystemSettings; // Import SystemSettings model class
import com.ticketsystem.model.SystemSettingsRequest; // Import SystemSettingsRequest model class
import com.ticketsystem.service.SystemSettingsService; // Import SystemSettingsService for service layer operations
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation for dependency injection
import org.springframework.http.ResponseEntity; // Import ResponseEntity for HTTP response handling
import org.springframework.web.bind.annotation.*; // Import annotations for RESTful web services

@RestController // Indicate that this class is a REST controller
@RequestMapping("/api/settings") // Map HTTP requests to /api/settings
@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
public class SystemSettingsController {
    @Autowired // Automatically inject the SystemSettingsService dependency
    private SystemSettingsService settingsService; // Declare the system settings service

    @GetMapping // Map GET requests to the base URL
    public ResponseEntity<SystemSettings> retrieveSettings() { // Define method to retrieve system settings
        try {
            SystemSettings settings = settingsService.getSettings(); // Call the service to get the current settings
            return ResponseEntity.ok(settings); // Return the settings wrapped in an HTTP OK response
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // Return an HTTP Internal Server Error response if an exception occurs
        }
    }

    @PostMapping // Map POST requests to the base URL
    public ResponseEntity<String> updateSettings(@RequestBody SystemSettingsRequest request) { // Define method to update system settings
        try {
            SystemSettings settings = new SystemSettings( // Create a new SystemSettings object from the request
                    request.getTotalTickets(),
                    request.getTicketReleaseRate(),
                    request.getCustomerRetrievalRate(),
                    request.getMaxTicketCapacity()
            );
            settingsService.updateSettings(settings); // Call the service to update the settings
            return ResponseEntity.ok("Settings updated successfully"); // Return a success message wrapped in an HTTP OK response
        } catch (Exception e) {
            return ResponseEntity.internalServerError() // Return an HTTP Internal Server Error response if an exception occurs
                    .body("Settings update failed: " + e.getMessage()); // Include the exception message in the response body
        }
    }
}
