import java.math.BigDecimal; // Import BigDecimal class for precise representation of ticket price

public class Ticket {
    private String Movie_Name; // Movie name for the ticket
    private String Hall_number; // Hall number where the movie is shown
    private int Ticket_ID; // Unique ID for the ticket
    private BigDecimal Ticket_price; // Price of the ticket

    // Constructor to initialize the ticket details
    public Ticket(String Movie_Name, String Hall_number, int Ticket_ID, BigDecimal Ticket_price) {
        this.Movie_Name = Movie_Name; // Set the movie name
        this.Hall_number = Hall_number; // Set the hall number
        this.Ticket_ID = Ticket_ID; // Set the ticket ID
        this.Ticket_price = Ticket_price; // Set the ticket price
    }

    // Getter for movie name
    public String getMovie_Name() {
        return Movie_Name; // Return the movie name
    }

    // Setter for movie name
    public void setMovie_Name(String Movie_Name) {
        this.Movie_Name = Movie_Name; // Set the movie name
    }

    // Getter for hall number
    public String getHall_number() {
        return Hall_number; // Return the hall number
    }

    // Setter for hall number
    public void setHall_number(String Hall_number) {
        this.Hall_number = Hall_number; // Set the hall number
    }

    // Getter for ticket ID
    public int getTicket_ID() {
        return Ticket_ID; // Return the ticket ID
    }

    // Setter for ticket ID
    public void setTicket_ID(int Ticket_ID) {
        this.Ticket_ID = Ticket_ID; // Set the ticket ID
    }

    // Getter for ticket price
    public BigDecimal getTicket_price() {
        return Ticket_price; // Return the ticket price
    }

    // Setter for ticket price
    public void setTicket_price(BigDecimal Ticket_price) {
        this.Ticket_price = Ticket_price; // Set the ticket price
    }

    // Override toString method to return ticket details as a string
    @Override
    public String toString() {
        return "Ticket details: \nMovie Name - " + Movie_Name +
                "\nHall Number - " + Hall_number +
                "\nTicket ID - " + Ticket_ID +
                "\nTicket Price - " + Ticket_price + "\n"; // Return string representation of the ticket
    }
}
