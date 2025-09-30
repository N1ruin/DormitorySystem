package by.niruin.dormitorySystem.domain.model;

import java.util.UUID;

public class Room {
    private final UUID uuid;
    private int number;
    private final byte capacity;
    private boolean availableForLiving;
    private boolean isMaleOnly;
    private final UUID dormitoryUuid;

    public Room(UUID uuid, byte capacity, int number, boolean availableForLiving, boolean isMaleOnly, UUID dormitoryUuid) {
        this.uuid = uuid;
        this.capacity = capacity;
        this.number = number;
        this.availableForLiving = availableForLiving;
        this.isMaleOnly = isMaleOnly;
        this.dormitoryUuid = dormitoryUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getNumber() {
        return number;
    }

    public byte getCapacity() {
        return capacity;
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

    public UUID getDormitoryUuid() {
        return dormitoryUuid;
    }
}
