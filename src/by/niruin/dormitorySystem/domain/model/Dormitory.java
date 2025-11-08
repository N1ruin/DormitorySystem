package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

public class Dormitory<ID> implements Identity<ID> {
    private final ID id;
    private final int number;
    private final int roomsCount;
    private final ID universityUuid;
    private boolean availableForLiving;

    public Dormitory(ID uuid, int number, int roomsCount, ID universityUuid, boolean availableForLiving) {
        this.id = uuid;
        this.number = number;
        this.roomsCount = roomsCount;
        this.universityUuid = universityUuid;
        this.availableForLiving = availableForLiving;
    }

    @Override
    public ID getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public ID getUniversityUuid() {
        return universityUuid;
    }

    public boolean isAvailableForLiving() {
        return availableForLiving;
    }

    public void setAvailableForLiving(boolean availableForLiving) {
        this.availableForLiving = availableForLiving;
    }
}
