package by.niruin.dormitorySystem.domain.model;

import java.util.Objects;

public class University {
    private final long id;
    private String name;
    private byte studyDuration;

    public University(long id, String name, byte studyDuration) {
        this.id = id;
        this.name = name;
        this.studyDuration = studyDuration;
    }

    public long getId() {
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
