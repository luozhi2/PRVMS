public abstract class Person {
    private String name;
    private int age;
    private String idNumber;

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, int age, String idNumber) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", ID Number: " + idNumber;
    }
}