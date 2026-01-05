package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.*;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;

@Component
public class DormitoryMapper extends AbstractEntityMapper<Dormitory> {
    @Override
    protected String entityFieldsToString(Dormitory dormitory) {
        return String.join(FIELDS_DELIMITER,
                dormitory.getId().toString(),
                String.valueOf(dormitory.getNumber()),
                String.valueOf(dormitory.getRoomsCount()),
                dormitory.getUniversityId().toString(),
                String.valueOf(dormitory.isAvailableForLiving()));
    }

    @Override
    protected Dormitory mapStringToEntity(String fields) {
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
