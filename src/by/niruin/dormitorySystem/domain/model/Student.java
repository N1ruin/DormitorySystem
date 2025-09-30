package by.niruin.dormitorySystem.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Student {
    private final UUID uuid;
    private final FullName fullName;
    private final Gender gender;
    private final UUID universityUuid;
    private final UUID dormitoryUuid;
    private UUID roomUuid;
    private int yearOfEntering;
    private final LocalDate deductionDate;

    public Student(UUID uuid, String firstName, String lastName, String fatherName, Gender gender,
                   LocalDate deductionDate, int yearOfEntering, UUID roomUuid, UUID dormitoryUuid, UUID universityUuid) {
        this.uuid = uuid;
        this.fullName = new FullName(firstName, fatherName, lastName);
        this.gender = gender;
        this.deductionDate = deductionDate;
        this.yearOfEntering = yearOfEntering;
        this.roomUuid = roomUuid;
        this.dormitoryUuid = dormitoryUuid;
        this.universityUuid = universityUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public UUID getUniversityUuid() {
        return universityUuid;
    }

    public UUID getDormitoryUuid() {
        return dormitoryUuid;
    }

    public UUID getRoomUuid() {
        return roomUuid;
    }

    public int getYearOfEntering() {
        return yearOfEntering;
    }

    public LocalDate getDeductionDate() {
        return deductionDate;
    }

    public void setRoomUuid(UUID roomUuid) {
        this.roomUuid = roomUuid;
    }

    public void setYearOfEntering(int yearOfEntering) {
        this.yearOfEntering = yearOfEntering;
    }
}
