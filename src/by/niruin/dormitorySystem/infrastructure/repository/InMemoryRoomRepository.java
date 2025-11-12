package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.RoomMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryRoomRepository extends InMemoryRepositoryBase<Room<UUID>, UUID> {
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/room.txt");

    public InMemoryRoomRepository(@Qualifier(value = RoomMapper.class) EntityMapper<Room<UUID>> roomMapper) {
        super(roomMapper, ROOMS_FILE_PATH);
    }
}
