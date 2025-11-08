package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

public class Room<ID> implements Identity<ID> {
    private final ID id;
    private int number;
    private final byte capacity;
    private boolean availableForLiving;
    private boolean isMaleOnly;
    private final ID dormitoryId;

    public Room(ID uuid, byte capacity, int number, boolean availableForLiving, boolean isMaleOnly, ID dormitoryUuid) {
        this.id = uuid;
        this.capacity = capacity;
        this.number = number;
        this.availableForLiving = availableForLiving;
        this.isMaleOnly = isMaleOnly;
        this.dormitoryId = dormitoryUuid;
    }

    @Override
    public ID getId() {
        return id;
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

    public ID getDormitoryId() {
        return dormitoryId;
    }
}
