package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.UUID;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;

@Component
public class UniversityMapper extends AbstractEntityMapper<University> {
    @Override
    protected String entityFieldsToString(University university) {
        return university.getId() + FIELDS_DELIMITER
               + university.getName() + FIELDS_DELIMITER
               + university.getStudyDuration();
    }

    @Override
    protected University mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != University.class.getDeclaredFields().length) {
            throw new EntityMappingException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        String name = parts[1];
        byte studyDuration = Byte.parseByte(parts[2]);

        return new University(id, name, studyDuration);
    }
}
