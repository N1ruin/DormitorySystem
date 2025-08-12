package by.niruin.dormitorySystem.domain.model;

public class Room {
    private final long id;
    private int number;
    private final byte capacity;
    private boolean availableForLiving;
    private boolean isMaleOnly;

    public Room(long id, byte capacity, int number, boolean availableForLiving, boolean isMaleOnly) {
        this.id = id;
        this.capacity = capacity;
        this.number = number;
        this.availableForLiving = availableForLiving;
        this.isMaleOnly = isMaleOnly;
    }

    public long getId() {
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
}
