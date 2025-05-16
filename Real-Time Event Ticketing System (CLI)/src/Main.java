import java.util.Scanner; // Import the Scanner class for user input

public class Main {

    public static void main(String[] args) {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        Configuration config = null; // Initialize config object

        // Welcome message for the user
        System.out.println("""
                -------=========================================================================-------
               |                          Welcome to The Cinema Ticket System                          |
                -------=========================================================================-------""");

        // Check if the user wants to load existing configuration or create a new one
        String loadConfigChoice;
        while (true) {  // Input validation for loading existing configuration
            System.out.print("Do you want to load the existing configuration? (yes/no): ");
            loadConfigChoice = scanner.nextLine().trim().toLowerCase(); // Get user input and convert to lowercase

            if (loadConfigChoice.equals("yes") || loadConfigChoice.equals("no")) {
                break; // Valid input, exit the loop
            } else {
                System.out.println("\nInvalid input. Please enter 'yes' or 'no'.");
            }
        }

        if (loadConfigChoice.equals("yes")) {
            try {
                config = Configuration.loadFromFile("config.json"); // Load configuration from file

                if (config != null) {
                    System.out.println("\nConfiguration loaded successfully\n\n");
                    System.out.println(config); // Print the loaded configuration
                } else {
                    System.out.println("\nNo configuration file found or an error occurred while loading.\n");
                }
            } catch (Exception e) {
                System.out.println("\nError loading configuration: " + e.getMessage());
            }
        }
        else if (loadConfigChoice.equals("no")) {
            try {
                // Prompt user for configuration input
                System.out.println("\n\n    Enter the following system configuration parameters:");

                System.out.print("\nTotal number of Tickets: ");
                int totalTickets = getValidInput(scanner); // Get valid input for total tickets

                System.out.print("Ticket Release Rate: ");
                int ticketReleaseRate = getValidInput(scanner); // Get valid input for ticket release rate

                System.out.print("Customer Retrieval Rate: ");
                int customerRetrievalRate = getValidInput(scanner); // Get valid input for customer retrieval rate

                System.out.print("Maximum Ticket Capacity: ");
                int maxTicketCapacity = getValidInput(scanner); // Get valid input for maximum ticket capacity

                // Create new configuration object
                config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
                config.saveToFile("config.json"); // Save the configuration to a file
            } catch (Exception e) {
                System.out.println("\nError while creating and saving configuration: " + e.getMessage());
            }
        }

        if (config != null) {
            try {
                // Initialize TicketPool with the maximum ticket capacity
                TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

                // Create and start vendor threads
                Vendor[] vendor = new Vendor[5]; // Array for 5 vendors
                for (int i = 0; i < vendor.length; i++) {
                    vendor[i] = new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate());
                    Thread vendor_thread = new Thread(vendor[i], "Vendor:" + i); // Create vendor thread
                    vendor_thread.start(); // Start the vendor thread
                }

                // Create and start customer threads
                Customer[] customer = new Customer[3]; // Array for 3 customers
                for (int i = 0; i < customer.length; i++) {
                    customer[i] = new Customer(ticketPool, config.getCustomerRetrievalRate(), 5);
                    Thread customer_thread = new Thread(customer[i], "Customer:" + i); // Create customer thread
                    customer_thread.start(); // Start the customer thread
                }
            } catch (NullPointerException e) {
                System.out.println("\nConfiguration object is null. Cannot proceed with ticket pool or thread initialization.");
            } catch (Exception e) {
                System.out.println("\nAn error occurred during ticket pool or thread initialization: " + e.getMessage());
            }
        } else {
            System.out.println("\nConfiguration not set. Exiting the program.");
        }

        scanner.close(); // Close the scanner
    }

    // Method to get valid integer input from the user
    private static int getValidInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("\nInvalid input. Please enter a valid number: ");
            scanner.next(); // Consume invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline character to avoid issues with scanner
        return input; // Return valid integer input
    }
}
