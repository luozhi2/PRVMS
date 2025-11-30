import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    // Basic attributes
    private String rideName;
    private String rideType; // e.g., Roller Coaster, Carousel
    private Employee operator; // Employee in charge of the ride

    // Queue and history storage
    private Queue<Visitor> waitingQueue;
    private LinkedList<Visitor> rideHistory;

    // Operation-related attributes
    private int maxRider; // Maximum riders per cycle
    private int numOfCycles; // Number of completed cycles (initial: 0)

    // Constructor
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    // Getter and Setter methods
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // ------------------------------
    // Part 3: Queue-related Methods
    // ------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        waitingQueue.offer(visitor);
        System.out.println("Visitor " + visitor.getName() + " has been added to " + rideName + " waiting queue");
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.println("Visitor " + removed.getName() + " has been removed from " + rideName + " waiting queue");
        } else {
            System.out.println(rideName + " waiting queue is empty - cannot remove visitor");
        }
        return removed;
    }

    @Override
    public void printQueue() {
        System.out.println("\n" + rideName + " Waiting Queue (Current count: " + waitingQueue.size() + "):");
        if (waitingQueue.isEmpty()) {
            System.out.println("No visitors in queue");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingQueue) {
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------
    // Part 4A: Ride History Methods
    // ------------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println("Visitor " + visitor.getName() + " has been added to " + rideName + " ride history");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        // Identify visitors by ID number (unique identifier)
        for (Visitor v : rideHistory) {
            if (v.getIdNumber().equals(visitor.getIdNumber())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n" + rideName + " Ride History (Total count: " + rideHistory.size() + "):");
        if (rideHistory.isEmpty()) {
            System.out.println("No ride records available");
            return;
        }
        // Traverse using Iterator
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------
    // Part 4B: Sort Ride History
    // ------------------------------
    public void sortRideHistory(Comparator<Visitor> comparator) {
        Collections.sort(rideHistory, comparator);
        System.out.println("\n" + rideName + " ride history has been sorted");
    }

    // ------------------------------
    // Part 5: Run One Ride Cycle
    // ------------------------------
    @Override
    public void runOneCycle() {
        // Check if operator is assigned
        if (operator == null) {
            System.out.println("Error: " + rideName + " has no assigned operator - cannot run");
            return;
        }
        // Check if queue is empty
        if (waitingQueue.isEmpty()) {
            System.out.println("Error: " + rideName + " waiting queue is empty - cannot run");
            return;
        }

        System.out.println("\n" + rideName + " starting cycle " + (numOfCycles + 1) + " (Max riders: " + maxRider + ")");
        int count = 0;
        // Move up to maxRider visitors from queue to history
        while (!waitingQueue.isEmpty() && count < maxRider) {
            Visitor visitor = waitingQueue.poll();
            rideHistory.add(visitor);
            System.out.println("Visitor " + visitor.getName() + " has ridden " + rideName);
            count++;
        }
        numOfCycles++;
        System.out.println(rideName + " cycle " + numOfCycles + " completed - " + count + " visitors rode this cycle");
    }

    // ------------------------------
    // Part 6: Export History to File
    // ------------------------------
    public void exportRideHistory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.write("Name,Age,IDNumber,TicketNumber,VisitDate");
            writer.newLine();
            // Write each visitor's data
            for (Visitor visitor : rideHistory) {
                writer.write(String.format("%s,%d,%s,%s,%s",
                        visitor.getName(),
                        visitor.getAge(),
                        visitor.getIdNumber(),
                        visitor.getTicketNumber(),
                        visitor.getVisitDate()));
                writer.newLine();
            }
            System.out.println("\n" + rideName + " ride history successfully exported to: " + filePath);
        } catch (IOException e) {
            System.err.println("Export error: " + e.getMessage());
        }
    }

    // ------------------------------
    // Part 7: Import History from File
    // ------------------------------
    public void importRideHistory(String filePath) {
        // Clear existing history
        rideHistory.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;
            // Read file content
            while ((line = reader.readLine()) != null) {
                lineNum++;
                // Skip header line
                if (lineNum == 1) continue;
                // Split CSV fields
                String[] fields = line.split(",");
                if (fields.length != 5) {
                    System.err.println("Warning: Invalid format in line " + lineNum + " - skipping record");
                    continue;
                }
                // Create Visitor object
                Visitor visitor = new Visitor(
                        fields[0], // Name
                        Integer.parseInt(fields[1]), // Age
                        fields[2], // ID Number
                        fields[3], // Ticket Number
                        fields[4]  // Visit Date
                );
                rideHistory.add(visitor);
            }
            System.out.println("\nSuccessfully imported " + rideHistory.size() + " ride records from " + filePath + " to " + rideName);
        } catch (FileNotFoundException e) {
            System.err.println("Import error: File not found - " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Import error: Invalid age format - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Import error: " + e.getMessage());
        }
    }
}