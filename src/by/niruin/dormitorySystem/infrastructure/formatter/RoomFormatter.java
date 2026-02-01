package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.room.RoomInfoDto;

public interface RoomFormatter {
    String formatRoomsToRoomsInfo(RoomInfoDto... dtos);
}
