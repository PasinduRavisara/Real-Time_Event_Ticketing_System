package com.ticketsystem.repository; // Define the package for the repository class

import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper for JSON parsing and writing
import com.ticketsystem.model.SystemSettings; // Import SystemSettings model class
import org.springframework.stereotype.Repository; // Import Repository annotation for Spring repository
import java.io.File; // Import File class for file handling
import java.io.IOException; // Import IOException for handling IO exceptions
import java.nio.file.Paths; // Import Paths class for path operations

@Repository // Indicate that this class is a Spring repository
public class SystemSettingsRepository {
    private final String SETTINGS_FILE = "settings.json"; // Define the settings file name
    private final ObjectMapper mapper = new ObjectMapper(); // Create an ObjectMapper instance

    // Method to retrieve system settings from the JSON file
    public SystemSettings retrieveSettings() {
        File file = Paths.get(SETTINGS_FILE).toFile(); // Get the file object for the settings file
        if (!file.exists()) { // Check if the file does not exist
            return new SystemSettings(0, 0, 0, 50); // Return default values if the file is not found
        }
        try {
            return mapper.readValue(file, SystemSettings.class); // Read and return the settings from the file
        } catch (IOException e) { // Catch any IO exceptions
            throw new RuntimeException("Failed to read settings: " + e.getMessage()); // Throw a runtime exception with an error message
        }
    }

    // Method to persist system settings to the JSON file
    public void persistSettings(SystemSettings settings) {
        try {
            mapper.writeValue(Paths.get(SETTINGS_FILE).toFile(), settings); // Write the settings to the file
        } catch (IOException e) {
            throw new RuntimeException("Failed to save settings: " + e.getMessage()); // Throw a runtime exception with an error message
        }
    }
}
