package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.time.LocalDate;

public class Student<ID> implements Identity<ID> {
    private final ID id;
    private final FullName fullName;
    private final Gender gender;
    private final ID universityUuid;
    private ID roomId;
    private int yearOfEntering;
    private final LocalDate deductionDate;

    public Student(ID uuid, String firstName, String lastName, String fatherName, Gender gender,
                   LocalDate deductionDate, int yearOfEntering, ID roomUuid, ID universityUuid) {
        this.id = uuid;
        this.fullName = new FullName(firstName, fatherName, lastName);
        this.gender = gender;
        this.deductionDate = deductionDate;
        this.yearOfEntering = yearOfEntering;
        this.roomId = roomUuid;
        this.universityUuid = universityUuid;
    }

    @Override
    public ID getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public ID getUniversityUuid() {
        return universityUuid;
    }

    public ID getRoomId() {
        return roomId;
    }

    public int getYearOfEntering() {
        return yearOfEntering;
    }

    public LocalDate getDeductionDate() {
        return deductionDate;
    }

    public void setRoomId(ID roomUuid) {
        this.roomId = roomUuid;
    }

    public void setYearOfEntering(int yearOfEntering) {
        this.yearOfEntering = yearOfEntering;
    }
}
