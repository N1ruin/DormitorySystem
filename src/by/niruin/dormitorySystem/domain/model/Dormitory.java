package by.niruin.dormitorySystem.domain.model;

public class Dormitory {
    private final long id;
    private final int number;
    private final int roomsCount;
    private University university;
    private boolean availableForLiving;

    public Dormitory(long id, int number, int roomsCount, University university, boolean availableForLiving) {
        this.id = id;
        this.number = number;
        this.roomsCount = roomsCount;
        this.university = university;
        this.availableForLiving = availableForLiving;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public University getUniversity() {
        return university;
    }

    public boolean isAvailableForLiving() {
        return availableForLiving;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setAvailableForLiving(boolean availableForLiving) {
        this.availableForLiving = availableForLiving;
    }
}
