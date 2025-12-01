import java.util.Scanner;

public class AssignmentTwo {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Theme Park Visitor Management System (PRVMS) ===");
        while (true) {
            System.out.println("\nPlease select a function module to demonstrate:");
            System.out.println("1.  Waiting Queue Management");
            System.out.println("2.  Ride History Recording");
            System.out.println("3.  Sort Ride History");
            System.out.println("4.  Run Ride Cycle");
            System.out.println("5. Export History to File");
            System.out.println("6. Import History from File");
            System.out.println("0. Exit System");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    partThree();
                    break;
                case 2:
                    partFourA();
                    break;
                case 3:
                    partFourB();
                    break;
                case 4:
                    partFive();
                    break;
                case 5:
                    partSix();
                    break;
                case 6:
                    partSeven();
                    break;
                case 0:
                    System.out.println("Thank you for using the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    // Part 3: Waiting Queue Management Demo
    private static void partThree() {
        System.out.println("\n===  Waiting Queue Management Demo ===");
        // Create operator
        Employee operator = new Employee("Zhang San", 30, "110101199501011234", "EMP001", "Roller Coaster Operations");
        // Create ride
        Ride rollerCoaster = new Ride("Thunder Coaster", "Roller Coaster", operator, 8);

        // Add 5 visitors to queue
        rollerCoaster.addVisitorToQueue(new Visitor("Li Si", 25, "110101199802021234", "TICK001", "2025-11-28"));
        rollerCoaster.addVisitorToQueue(new Visitor("Wang Wu", 18, "110101200703031234", "TICK002", "2025-11-28"));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhao Liu", 35, "110101199004041234", "TICK003", "2025-11-28"));
        rollerCoaster.addVisitorToQueue(new Visitor("Sun Qi", 22, "110101200305051234", "TICK004", "2025-11-28"));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhou Ba", 28, "110101199706061234", "TICK005", "2025-11-28"));

        // Remove one visitor
        rollerCoaster.removeVisitorFromQueue();

        // Print queue
        rollerCoaster.printQueue();
    }

    // Part 4A: Ride History Recording Demo
    private static void partFourA() {
        System.out.println("\n===  Ride History Recording Demo ===");
        Employee operator = new Employee("Qian Jiu", 28, "110101199707071234", "EMP002", "Carousel Operations");
        Ride carousel = new Ride("Dream Carousel", "Carousel", operator, 12);

        // Add 5 visitors to history
        Visitor v1 = new Visitor("Wu Shi", 10, "110101201508081234", "TICK006", "2025-11-28");
        Visitor v2 = new Visitor("Zheng Shiyi", 15, "110101201009091234", "TICK007", "2025-11-28");
        Visitor v3 = new Visitor("Wang Shi'er", 20, "110101200510101234", "TICK008", "2025-11-28");
        Visitor v4 = new Visitor("Feng Shisan", 25, "110101200011111234", "TICK009", "2025-11-28");
        Visitor v5 = new Visitor("Chen Shisi", 30, "110101199512121234", "TICK010", "2025-11-28");

        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // Check if a visitor exists in history
        Visitor checkVisitor = new Visitor("Zheng Shiyi", 15, "110101201009091234", "TICK007", "2025-11-28");
        boolean exists = carousel.checkVisitorFromHistory(checkVisitor);
        System.out.println("\nDoes visitor " + checkVisitor.getName() + " exist in ride history? " + (exists ? "Yes" : "No"));

        // Print history count
        System.out.println("Total number of visitors in history: " + carousel.numberOfVisitors());

        // Print history
        carousel.printRideHistory();
    }

    // Part 4B: Sort Ride History Demo
    private static void partFourB() {
        System.out.println("\n===  Sort Ride History Demo ===");
        Employee operator = new Employee("Chu Shiwu", 32, "110101199301131234", "EMP003", "Pirate Ship Operations");
        Ride pirateShip = new Ride("Pirate Ship", "Swing Ride", operator, 10);

        // Add 5 visitors to history (random ages and names)
        pirateShip.addVisitorToHistory(new Visitor("Zhao Yi", 22, "110101200301141234", "TICK011", "2025-11-28"));
        pirateShip.addVisitorToHistory(new Visitor("Qian Er", 35, "110101199001151234", "TICK012", "2025-11-28"));
        pirateShip.addVisitorToHistory(new Visitor("Sun San", 22, "110101200301161234", "TICK013", "2025-11-28"));
        pirateShip.addVisitorToHistory(new Visitor("Li Si", 18, "110101200701171234", "TICK014", "2025-11-28"));
        pirateShip.addVisitorToHistory(new Visitor("Zhou Wu", 35, "110101199001181234", "TICK015", "2025-11-28"));

        // Print unsorted history
        System.out.println("\nRide History (Unsorted):");
        pirateShip.printRideHistory();

        // Sort using custom comparator (age descending, name ascending)
        pirateShip.sortRideHistory(new VisitorComparator());

        // Print sorted history
        System.out.println("\nRide History (Sorted by Age Descending, Name Ascending):");
        pirateShip.printRideHistory();
    }

    // Part 5: Run Ride Cycle Demo
    private static void partFive() {
        System.out.println("\n===  Run Ride Cycle Demo ===");
        // Create operator
        Employee operator = new Employee("Wu Liu", 29, "110101199601191234", "EMP004", "Log Flume Operations");
        // Create ride (max 6 riders per cycle)
        Ride logFlume = new Ride("Log Flume", "Water Ride", operator, 6);

        // Add 10 visitors to queue
        for (int i = 1; i <= 10; i++) {
            logFlume.addVisitorToQueue(new Visitor(
                    "Visitor" + i,
                    18 + (i % 20), // Age between 18-37
                    "110101" + (2000 - i) + "01201234", // Simulated ID number
                    "TICK0" + (16 + i),
                    "2025-11-28"
            ));
        }

        // Print queue before cycle
        System.out.println("\nWaiting Queue (Before Cycle):");
        logFlume.printQueue();

        // Run one cycle
        logFlume.runOneCycle();

        // Print queue after cycle
        System.out.println("\nWaiting Queue (After Cycle):");
        logFlume.printQueue();

        // Print ride history
        logFlume.printRideHistory();
    }

    // Part 6: Export History to File Demo
    private static void partSix() {
        System.out.println("\n===  Export History to File Demo ===");
        Employee operator = new Employee("Zheng Qi", 31, "110101199401211234", "EMP005", "Ferris Wheel Operations");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Observation Ride", operator, 24);

        // Add 5 visitors to history
        ferrisWheel.addVisitorToHistory(new Visitor("Wang Xiaoming", 20, "110101200501221234", "TICK027", "2025-11-28"));
        ferrisWheel.addVisitorToHistory(new Visitor("Li Xiaohong", 22, "110101200301231234", "TICK028", "2025-11-28"));
        ferrisWheel.addVisitorToHistory(new Visitor("Zhang Xiaogang", 25, "110101200001241234", "TICK029", "2025-11-28"));
        ferrisWheel.addVisitorToHistory(new Visitor("Liu Xiaoli", 19, "110101200601251234", "TICK030", "2025-11-28"));
        ferrisWheel.addVisitorToHistory(new Visitor("Chen Xiaoqiang", 23, "110101200201261234", "TICK031", "2025-11-28"));

        // Export to CSV file (in project root directory)
        String filePath = "ferris_wheel_history.csv";
        ferrisWheel.exportRideHistory(filePath);
    }

    // Part 7: Import History from File Demo
    private static void partSeven() {
        System.out.println("\n===  Import History from File Demo ===");
        Employee operator = new Employee("Yang Ba", 33, "110101199201271234", "EMP006", "Ferris Wheel Operations");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Observation Ride", operator, 24);

        // Import from previously exported file
        String filePath = "ferris_wheel_history.csv";
        ferrisWheel.importRideHistory(filePath);

        // Verify import result
        System.out.println("\nNumber of visitors after import: " + ferrisWheel.numberOfVisitors());
        ferrisWheel.printRideHistory();
    }
}