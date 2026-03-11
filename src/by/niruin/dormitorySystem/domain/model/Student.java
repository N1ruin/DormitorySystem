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
    private final LocalDate startEducationDate;
    private final LocalDate endingEducationDate;
    private final LocalDate roomCheckInDate;
    private final LocalDate roomCheckOutDate;

    public Student(UUID id, FullName fullName, Gender gender, UUID universityUuid, UUID roomId, UUID dormitoryId,
                   LocalDate startEducationDate, LocalDate expulsionDate, LocalDate roomCheckInDate, LocalDate roomCheckOutDate) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.universityUuid = universityUuid;
        this.roomId = roomId;
        this.dormitoryId = dormitoryId;
        this.startEducationDate = startEducationDate;
        this.endingEducationDate = expulsionDate;
        this.roomCheckInDate = roomCheckInDate;
        this.roomCheckOutDate = roomCheckOutDate;
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

    public LocalDate getStartEducationDate() {
        return startEducationDate;
    }

    public LocalDate getEndingEducationDate() {
        return endingEducationDate;
    }

    public LocalDate getRoomCheckOutDate() {
        return roomCheckOutDate;
    }

    public LocalDate getRoomCheckInDate() {
        return roomCheckInDate;
    }
}
