package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.UUID;

public class Dormitory implements Identity {
    private final UUID id;
    private final int number;
    private final int roomsCount;
    private final UUID universityId;
    private boolean availableForLiving;

    public Dormitory(UUID uuid, int number, int roomsCount, UUID universityUuid, boolean availableForLiving) {
        this.id = uuid;
        this.number = number;
        this.roomsCount = roomsCount;
        this.universityId = universityUuid;
        this.availableForLiving = availableForLiving;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public UUID getUniversityId() {
        return universityId;
    }

    public boolean isAvailableForLiving() {
        return availableForLiving;
    }

    public void setAvailableForLiving(boolean availableForLiving) {
        this.availableForLiving = availableForLiving;
    }
}
