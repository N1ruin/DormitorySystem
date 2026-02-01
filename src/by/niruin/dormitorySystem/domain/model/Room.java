package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.UUID;

public class Room implements Identity {
    private final UUID id;
    private int number;
    private byte capacity;
    private boolean availableForLiving;
    private boolean isMaleOnly;
    private final UUID dormitoryId;

    public Room(UUID uuid, byte capacity, int number, boolean availableForLiving, boolean isMaleOnly, UUID dormitoryUuid) {
        this.id = uuid;
        this.capacity = capacity;
        this.number = number;
        this.availableForLiving = availableForLiving;
        this.isMaleOnly = isMaleOnly;
        this.dormitoryId = dormitoryUuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailableForLiving() {
        return availableForLiving;
    }

    public boolean isMaleOnly() {
        return isMaleOnly;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAvailableForLiving(boolean availableForLiving) {
        this.availableForLiving = availableForLiving;
    }

    public void setMaleOnly(boolean maleOnly) {
        isMaleOnly = maleOnly;
    }

    public UUID getDormitoryId() {
        return dormitoryId;
    }
}
