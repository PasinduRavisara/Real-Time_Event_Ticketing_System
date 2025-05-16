import java.math.BigDecimal; // Import BigDecimal class for ticket price representation

public class Vendor implements Runnable {
    private TicketPool ticketPool; // Ticket pool shared between vendors and customers
    private int totalTickets; // Total number of tickets to be released by this vendor
    private int ticketReleaseRate; // Rate at which tickets are released (in seconds)

    // Constructor to initialize the vendor with ticket pool, total tickets, and release rate
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool; // Set the ticket pool
        this.totalTickets = totalTickets; // Set the total tickets
        this.ticketReleaseRate = ticketReleaseRate; // Set the ticket release rate
    }

    // Implement the run method from Runnable interface
    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) { // Loop to release all tickets
            Ticket ticket = new Ticket("Test movie", "H1", i, new BigDecimal("1000")); // Create a new ticket
            ticketPool.addTicket(ticket); // Add the ticket to the pool

            // Ticket release frequency
            try {
                Thread.sleep(ticketReleaseRate * 1000); // Sleep for the specified release rate in seconds
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted."); // Print interruption message
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }

        System.out.println(Thread.currentThread().getName() + " has released all tickets."); // Print completion message
    }
}
