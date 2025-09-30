package by.niruin.dormitorySystem.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Dormitory {
    private final UUID uuid;
    private final int number;
    private final int roomsCount;
    private final UUID universityUuid;
    private boolean availableForLiving;
    private final List<UUID> roomUuids = new ArrayList<>();

    public Dormitory(UUID uuid, int number, int roomsCount, UUID universityUuid, boolean availableForLiving) {
        this.uuid = uuid;
        this.number = number;
        this.roomsCount = roomsCount;
        this.universityUuid = universityUuid;
        this.availableForLiving = availableForLiving;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getNumber() {
        return number;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public UUID getUniversityUuid() {
        return universityUuid;
    }

    public boolean isAvailableForLiving() {
        return availableForLiving;
    }

    public void setAvailableForLiving(boolean availableForLiving) {
        this.availableForLiving = availableForLiving;
    }

    public List<UUID> getRoomUuids() {
        return roomUuids;
    }
}
