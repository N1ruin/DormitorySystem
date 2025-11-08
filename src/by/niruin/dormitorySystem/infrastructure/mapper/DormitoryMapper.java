package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Dormitory;

import java.util.*;

public class DormitoryMapper extends AbstractEntityMapper<Dormitory> {
    @Override
    protected int getFieldsCount() {
        return 5;
    }

    @Override
    protected String entityFieldsToString(Dormitory dormitory) {
        StringBuilder sb = new StringBuilder();
        sb.append(dormitory.getId().toString()).append(FIELDS_DELIMITER);
        sb.append(dormitory.getNumber()).append(FIELDS_DELIMITER);
        sb.append(dormitory.getRoomsCount()).append(FIELDS_DELIMITER);
        sb.append(dormitory.getUniversityUuid().toString()).append(FIELDS_DELIMITER);
        sb.append(dormitory.isAvailableForLiving()).append(FIELDS_DELIMITER);

        return sb.toString();
    }

    @Override
    protected Dormitory mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        UUID uuid = UUID.fromString(parts[0]);
        int number = Integer.parseInt(parts[1]);
        int roomsCount = Integer.parseInt(parts[2]);
        UUID universityId = UUID.fromString(parts[3]);
        boolean availableForLiving = Boolean.parseBoolean(parts[4]);

        return new Dormitory(uuid, number, roomsCount, universityId, availableForLiving);
    }
}
