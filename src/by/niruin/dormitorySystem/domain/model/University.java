package by.niruin.dormitorySystem.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class University {
    private final UUID uuid;
    private String name;
    private byte studyDuration;
    private final List<UUID> dormitories = new ArrayList<>();

    public University(UUID uuid, String name, byte studyDuration) {
        this.uuid = uuid;
        this.name = name;
        this.studyDuration = studyDuration;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public byte getStudyDuration() {
        return studyDuration;
    }

    public List<UUID> getDormitories() {
        return dormitories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyDuration(byte studyDuration) {
        this.studyDuration = studyDuration;
    }

}
