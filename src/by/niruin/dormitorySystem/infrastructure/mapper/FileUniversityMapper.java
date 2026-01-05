package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.FIELDS_DELIMITER;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.NEW_LINE_SYMBOL;

@Component
public class FileUniversityMapper implements UniversityMapper {

    @Override
    public String mapUniversitiesToString(Collection<University> universities) {
        return universities.stream()
                .map(this::userFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    @Override
    public Collection<University> mapStringToUniversities(String usersData) {
        if (usersData == null || usersData.trim().isEmpty()) {
            return List.of();
        }

        String[] usersFields = usersData.split(NEW_LINE_SYMBOL);

        return Arrays.stream(usersFields)
                .map(this::mapStringToUser)
                .toList();
    }

    private String userFieldsToString(University university) {
        return university.getId() + FIELDS_DELIMITER
               + university.getName() + FIELDS_DELIMITER
               + university.getStudyDuration();
    }

    private University mapStringToUser(String fields) {
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
