import com.google.gson.Gson; // Import Gson library for JSON parsing
import com.google.gson.GsonBuilder; // Import GsonBuilder for custom Gson instances

import java.io.FileReader; // Import FileReader for reading files
import java.io.FileWriter; // Import FileWriter for writing files
import java.io.IOException; // Import IOException for handling IO exceptions

public class Configuration {
    private int totalTickets; // Total number of tickets available
    private int ticketReleaseRate; // Rate at which tickets are released
    private int customerRetrievalRate; // Rate at which customers retrieve tickets
    private int maxTicketCapacity; // Maximum capacity of tickets

    // Constructor to initialize configuration
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets; // Set total tickets
        this.ticketReleaseRate = ticketReleaseRate; // Set ticket release rate
        this.customerRetrievalRate = customerRetrievalRate; // Set customer retrieval rate
        this.maxTicketCapacity = maxTicketCapacity; // Set maximum ticket capacity
    }

    // Getter for total tickets
    public int getTotalTickets() {
        return totalTickets; // Return total tickets
    }

    // Getter for ticket release rate
    public int getTicketReleaseRate() {
        return ticketReleaseRate; // Return ticket release rate
    }

    // Getter for customer retrieval rate
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate; // Return customer retrieval rate
    }

    // Getter for maximum ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity; // Return maximum ticket capacity
    }

    // Save configuration to a JSON file
    public void saveToFile(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create Gson instance with pretty printing

        try (FileWriter writer = new FileWriter(filename)) { // Try-with-resources for FileWriter
            gson.toJson(this, writer); // Convert this configuration object to JSON and write to file
            System.out.println("\n\nConfiguration saved to " + filename); // Print success message
        } catch (IOException e) { // Catch any IO exceptions
            System.out.println("\nError saving configuration: " + e.getMessage()); // Print error message
        }
    }

    // Load configuration from a JSON file
    public static Configuration loadFromFile(String filename) {
        Gson gson = new Gson(); // Create Gson instance

        try (FileReader reader = new FileReader(filename)) { // Try-with-resources for FileReader
            return gson.fromJson(reader, Configuration.class); // Parse JSON file to Configuration object
        } catch (IOException e) { // Catch any IO exceptions
            System.out.println("Error loading configuration: " + e.getMessage()); // Print error message
            return null; // Return null if an error occurs
        }
    }

    // Override toString method to return configuration details as a string
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrivalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                "}\n"; // Return string representation of the configuration
    }
}
