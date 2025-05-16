public class Customer implements Runnable {
    private TicketPool ticketPool; // Ticket pool shared between vendors and customers
    private int customerRetrievalRate; // Rate at which customers retrieve tickets (in seconds)
    private int amount; // Number of tickets the customer wants to buy

    // Constructor to initialize the customer with ticket pool, retrieval rate, and amount
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int amount) {
        this.ticketPool = ticketPool; // Set the ticket pool
        this.customerRetrievalRate = customerRetrievalRate; // Set the customer retrieval rate
        this.amount = amount; // Set the amount of tickets to buy
    }

    // Implement the run method from Runnable interface
    @Override
    public void run() {
        for (int i = 0; i < amount; i++) { // Loop to buy the specified number of tickets
            Ticket ticket = ticketPool.removeTicket(); // Remove a ticket from the pool

            // Ticket buying frequency
            try {
                Thread.sleep(customerRetrievalRate * 1000); // Sleep for the specified retrieval rate in seconds
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted."); // Print interruption message
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }

        System.out.println(Thread.currentThread().getName() + " has finished buying tickets."); // Print completion message
    }
}
