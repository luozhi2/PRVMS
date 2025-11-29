public class Employee extends Person {
    private String employeeId;
    private String department; // e.g., Roller Coaster Operations, Customer Service

    // Default constructor
    public Employee() {}

    // Parameterized constructor
    public Employee(String name, int age, String idNumber, String employeeId, String department) {
        super(name, age, idNumber);
        this.employeeId = employeeId;
        this.department = department;
    }

    // Getter and Setter methods
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Department: " + department;
    }
}