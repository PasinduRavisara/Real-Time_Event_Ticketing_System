package com.ticketsystem.service; // Define the package for the service class

import com.ticketsystem.model.SystemSettings; // Import SystemSettings model class
import com.ticketsystem.repository.SystemSettingsRepository; // Import SystemSettingsRepository for repository operations
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation for dependency injection
import org.springframework.stereotype.Service; // Import Service annotation for Spring service

@Service // Indicate that this class is a Spring service
public class SystemSettingsService {
    private final SystemSettingsRepository settingsRepository; // Declare a field for the repository

    // Constructor to initialize the service with the repository
    @Autowired // Automatically inject the SystemSettingsRepository dependency
    public SystemSettingsService(SystemSettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository; // Set the repository
    }

    // Method to retrieve system settings
    public SystemSettings getSettings() {
        try {
            return settingsRepository.retrieveSettings(); // Call the repository to retrieve settings
        } catch (Exception e) {
            return new SystemSettings(0, 0, 0, 50); // Return default values if an exception occurs
        }
    }

    // Method to update system settings
    public void updateSettings(SystemSettings settings) {
        settingsRepository.persistSettings(settings); // Call the repository to persist settings
    }
}
