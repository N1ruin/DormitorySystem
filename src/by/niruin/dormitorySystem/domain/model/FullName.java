package by.niruin.dormitorySystem.domain.model;

public class FullName {
    private final String firstName;
    private String lastName;
    private final String fatherName;

    public FullName(String firstName, String fatherName, String lastName) {
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getShortName() {
        return "%s. %s. %s".formatted(firstName, lastName.charAt(0), fatherName.charAt(0));
    }

    @Override
    public String toString() {
        return "%s %s %s".formatted(firstName, lastName, fatherName);
    }
}
