package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

@Component
public class UniversityMapper extends AbstractEntityMapper<University<?>> {
    public UniversityMapper() {
    }

    @Override
    protected String entityFieldsToString(University<?> university) {
        return String.join(FIELDS_DELIMITER,
                university.getId().toString(),
                university.getName(),
                String.valueOf(university.getStudyDuration()));
    }

    @Override
    protected University<?> mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != University.class.getDeclaredFields().length) {
            throw new RuntimeException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        Object id = getIdObject(University.class, parts[0]);
        String name = parts[1];
        byte studyDuration = Byte.parseByte(parts[2]);

        return new University<>(id, name, studyDuration);
    }
}
