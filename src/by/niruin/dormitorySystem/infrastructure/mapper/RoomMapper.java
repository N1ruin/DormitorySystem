package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Room;

import java.util.*;

public class RoomMapper extends AbstractEntityMapper<Room> {
    @Override
    protected int getFieldsCount() {
        return 6;
    }

    @Override
    protected String entityFieldsToString(Room room) {
        return String.join(
                FIELDS_DELIMITER,
                room.getUuid().toString(),
                String.valueOf(room.getNumber()),
                String.valueOf(room.getCapacity()),
                String.valueOf(room.isAvailableForLiving()),
                String.valueOf(room.isMaleOnly()),
                room.getDormitoryUuid().toString());
    }

    @Override
    protected Room mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != getFieldsCount()) {
            throw new RuntimeException("");
        }

        UUID uuid = UUID.fromString(parts[0]);
        int number = Integer.parseInt(parts[1]);
        byte capacity = Byte.parseByte(parts[2]);
        boolean availableForLiving = Boolean.parseBoolean(parts[3]);
        boolean maleOnly = Boolean.parseBoolean(parts[4]);
        UUID dormitoryUuid = UUID.fromString(parts[5]);

        return new Room(uuid, capacity, number, availableForLiving, maleOnly, dormitoryUuid);
    }
}
