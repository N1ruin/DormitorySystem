package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.UUID;

public class University implements Identity {
    private final UUID id;
    private String name;
    private byte studyDuration;

    public University(UUID uuid, String name, byte studyDuration) {
        this.id = uuid;
        this.name = name;
        this.studyDuration = studyDuration;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getStudyDuration() {
        return studyDuration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyDuration(byte studyDuration) {
        this.studyDuration = studyDuration;
    }
}
