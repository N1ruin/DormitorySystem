package by.niruin.dormitorySystem.domain.model;

import java.util.Objects;

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return "%s %s. %s.".formatted(lastName, firstName.charAt(0), fatherName.charAt(0));
    }

    public String getFullNameString() {
        return "%s %s %s".formatted(lastName, firstName, fatherName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (o != this) {
            return false;
        }
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName)
               && Objects.equals(lastName, fullName.lastName)
               && Objects.equals(fatherName, fullName.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, fatherName);
    }
}
