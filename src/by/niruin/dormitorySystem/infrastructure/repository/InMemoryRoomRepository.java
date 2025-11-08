package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryRoomRepository extends InMemoryRepositoryBase<Room<UUID>, UUID> {
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryRoomRepository(EntityMapper<Room<UUID>> roomMapper) {
        super(roomMapper, ROOMS_FILE_PATH);
    }
}
