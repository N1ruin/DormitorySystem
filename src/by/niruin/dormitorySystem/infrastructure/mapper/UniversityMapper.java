package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.University;

import java.util.*;

public class UniversityMapper extends AbstractEntityMapper<University> {
    @Override
    protected int getFieldsCount() {
        return 3;
    }

    @Override
    protected String entityFieldsToString(University university) {
        StringBuilder sb = new StringBuilder();
        sb.append(university.getId().toString()).append(FIELDS_DELIMITER);
        sb.append(university.getName()).append(FIELDS_DELIMITER);
        sb.append(university.getStudyDuration()).append(FIELDS_DELIMITER);

        return sb.toString();
    }

    @Override
    protected University mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        UUID uuid = UUID.fromString(parts[0]);
        String name = parts[1];
        byte studyDuration = Byte.parseByte(parts[2]);

        return new University(uuid, name, studyDuration);
    }
}
