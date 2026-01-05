package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Room;

import java.util.Collection;

public interface RoomMapper {
    String mapRoomsToString(Collection<Room> rooms);

    Collection<Room> mapStringToRooms(String roomsData);
}
