import java.util.LinkedList; // Import LinkedList class for queue implementation
import java.util.Queue; // Import Queue interface

public class TicketPool {
    private Queue<Ticket> ticket_queue; // Queue to hold the tickets
    private int maxTicketCapacity; // Maximum capacity of the ticket pool

    // Constructor to initialize the ticket pool with a maximum capacity
    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity; // Set the maximum ticket capacity
        this.ticket_queue = new LinkedList<>(); // Initialize the ticket queue as a LinkedList
    }

    // Add Ticket Method (Used by vendors)
    public synchronized void addTicket(Ticket ticket) {
        while (ticket_queue.size() >= maxTicketCapacity) { // While the ticket queue is full
            try {
                wait(); // Wait until space is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                throw new RuntimeException(e.getMessage()); // Throw runtime exception if interrupted
            }
        }

        this.ticket_queue.add(ticket); // Add a ticket to the end of the queue

        System.out.println("Ticket added by = " + Thread.currentThread().getName() +
                "\nCurrent ticket pool size is " + ticket_queue.size()); // Print the current status of the ticket pool

        notifyAll(); // Wake up waiting customers
    }

    // Remove Ticket Method (Used by Customers)
    public synchronized Ticket removeTicket() {
        while (ticket_queue.isEmpty()) { // While the ticket queue is empty
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting, no tickets in the pool."); // Inform that the customer is waiting
                wait(); // Wait until tickets are available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                throw new RuntimeException(e.getMessage()); // Throw runtime exception if interrupted
            }
        }

        Ticket ticket = ticket_queue.poll(); // Remove a ticket from the front of the queue

        System.out.println("\nTicket bought by = " + Thread.currentThread().getName() +
                "\nCurrent ticket pool size is " + ticket_queue.size() +
                "\n" + ticket + "\n"); // Print the current status of the ticket pool and the ticket details

        notifyAll(); // Wake up waiting vendors
        return ticket; // Return the removed ticket
    }
}
