public class Visitor extends Person {
    private String ticketNumber;
    private String visitDate; // Format: yyyy-MM-dd

    // Default constructor
    public Visitor() {}

    // Parameterized constructor
    public Visitor(String name, int age, String idNumber, String ticketNumber, String visitDate) {
        super(name, age, idNumber);
        this.ticketNumber = ticketNumber;
        this.visitDate = visitDate;
    }

    // Getter and Setter methods
    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + ", Ticket Number: " + ticketNumber + ", Visit Date: " + visitDate;
    }
}