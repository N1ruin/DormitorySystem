package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.*;
import java.util.stream.Collectors;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;

@Component
public class FileDormitoryMapper implements DormitoryMapper {
    public static final String FIELDS_DELIMITER = ",";
    public static final String NEW_LINE_SYMBOL = "\n";

    @Override
    public String mapDormitoriesToString(Collection<Dormitory> dormitories) {
        return dormitories.stream()
                .map(this::entityFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    @Override
    public Collection<Dormitory> mapStringToDormitories(String dormitoriesData) {
        if (dormitoriesData == null || dormitoriesData.trim().isEmpty()) {
            return List.of();
        }

        String[] dormitoryFields = dormitoriesData.split(NEW_LINE_SYMBOL);

        return Arrays.stream(dormitoryFields)
                .map(this::mapStringToEntity)
                .toList();
    }

    private String entityFieldsToString(Dormitory dormitory) {
        return String.join(FIELDS_DELIMITER,
                dormitory.getId().toString(),
                String.valueOf(dormitory.getNumber()),
                String.valueOf(dormitory.getRoomsCount()),
                dormitory.getUniversityId().toString(),
                String.valueOf(dormitory.isAvailableForLiving()));
    }

    private Dormitory mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != Dormitory.class.getDeclaredFields().length) {
            throw new EntityMappingException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        int number = Integer.parseInt(parts[1]);
        int roomsCount = Integer.parseInt(parts[2]);
        UUID universityId = UUID.fromString(parts[3]);
        boolean availableForLiving = Boolean.parseBoolean(parts[4]);

        return new Dormitory(id, number, roomsCount, universityId, availableForLiving);

    }
}
