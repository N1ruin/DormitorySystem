package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.*;
import java.util.stream.Collectors;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.FIELDS_DELIMITER;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.NEW_LINE_SYMBOL;

@Component
public class FileRoomMapper implements RoomMapper {

    @Override
    public String mapRoomsToString(Collection<Room> rooms) {
        return rooms.stream()
                .map(this::entityFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    @Override
    public Collection<Room> mapStringToRooms(String roomsData) {
        if (roomsData == null || roomsData.trim().isEmpty()) {
            return List.of();
        }

        String[] roomsFields = roomsData.split(NEW_LINE_SYMBOL);

        return Arrays.stream(roomsFields)
                .map(this::mapStringToEntity)
                .toList();
    }

    private String entityFieldsToString(Room room) {
        return String.join(
                FIELDS_DELIMITER,
                room.getId().toString(),
                String.valueOf(room.getNumber()),
                String.valueOf(room.getCapacity()),
                String.valueOf(room.isAvailableForLiving()),
                String.valueOf(room.isMaleOnly()),
                room.getDormitoryId().toString());
    }

    private Room mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != Room.class.getDeclaredFields().length) {
            throw new EntityMappingException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        int number = Integer.parseInt(parts[1]);
        byte capacity = Byte.parseByte(parts[2]);
        boolean availableForLiving = Boolean.parseBoolean(parts[3]);
        boolean maleOnly = Boolean.parseBoolean(parts[4]);
        UUID dormitoryUuid = UUID.fromString(parts[5]);

        return new Room(id, capacity, number, availableForLiving, maleOnly, dormitoryUuid);
    }
}
