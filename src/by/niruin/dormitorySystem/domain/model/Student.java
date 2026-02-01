package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.time.LocalDate;
import java.util.UUID;

public class Student implements Identity {
    private final UUID id;
    private final FullName fullName;
    private final Gender gender;
    private final UUID universityUuid;
    private UUID roomId;
    private UUID dormitoryId;
    private final LocalDate dateOfStartEducation;
    private final LocalDate dateOfEndingEducation;
    private final LocalDate dateOfRoomCheckIn;
    private final LocalDate dateOfRoomCheckOut;

    public Student(UUID id, FullName fullName, Gender gender, UUID universityUuid, UUID roomId, UUID dormitoryId,
                   LocalDate dateOfEntering, LocalDate expulsionDate, LocalDate dateOfRoomCheckIn, LocalDate dateOfRoomCheckOut) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.universityUuid = universityUuid;
        this.roomId = roomId;
        this.dormitoryId = dormitoryId;
        this.dateOfStartEducation = dateOfEntering;
        this.dateOfEndingEducation = expulsionDate;
        this.dateOfRoomCheckIn = dateOfRoomCheckIn;
        this.dateOfRoomCheckOut = dateOfRoomCheckOut;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public UUID getUniversityId() {
        return universityUuid;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomUuid) {
        this.roomId = roomUuid;
    }

    public UUID getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(UUID dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public LocalDate getDateOfStartEducation() {
        return dateOfStartEducation;
    }

    public LocalDate getDateOfEndingEducation() {
        return dateOfEndingEducation;
    }

    public LocalDate getDateOfRoomCheckOut() {
        return dateOfRoomCheckOut;
    }

    public LocalDate getDateOfRoomCheckIn() {
        return dateOfRoomCheckIn;
    }
}
