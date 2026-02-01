package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.time.LocalDate;
import java.util.UUID;

public class Student implements Identity {
    private final UUID id;
    private final FullName fullName;
    private final Gender gender;
    private UUID universityUuid;
    private UUID roomId;
    private UUID dormitoryId;
    private final LocalDate dateOfEntering; //дата поступления в ВУЗ
    private final LocalDate expulsionDate; //дата отчисления из ВУЗа
    private final LocalDate roomCheckIn; // дата заселения в общежитие
    private final LocalDate roomCheckOut; //дата выселения из ВУЗа

    public Student(UUID id, FullName fullName, Gender gender, UUID universityUuid, UUID roomId, UUID dormitoryId,
                   LocalDate dateOfEntering, LocalDate expulsionDate, LocalDate roomCheckIn, LocalDate roomCheckOut) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.universityUuid = universityUuid;
        this.roomId = roomId;
        this.dormitoryId = dormitoryId;
        this.dateOfEntering = dateOfEntering;
        this.expulsionDate = expulsionDate;
        this.roomCheckIn = roomCheckIn;
        this.roomCheckOut = roomCheckOut;
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

    public UUID getUniversityUuid() {
        return universityUuid;
    }

    public void setUniversityUuid(UUID universityUuid) {
        this.universityUuid = universityUuid;
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

    public LocalDate getDateOfEntering() {
        return dateOfEntering;
    }

    public LocalDate getExpulsionDate() {
        return expulsionDate;
    }

    public LocalDate getRoomCheckOut() {
        return roomCheckOut;
    }

    public LocalDate getRoomCheckIn() {
        return roomCheckIn;
    }
}
