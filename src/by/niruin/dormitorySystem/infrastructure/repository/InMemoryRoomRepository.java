package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryRoomRepository extends InMemoryRepositoryBase<Room> implements RoomRepository {
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryRoomRepository(EntityMapper<Room> roomMapper) {
        super(roomMapper, ROOMS_FILE_PATH, Room::getUuid);
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid == null || !entities.containsKey(uuid)) {
            throw new EntityNotFoundException(uuid);
        }
        entities.remove(uuid);
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Room findById(UUID uuid) {
        return Optional.ofNullable(entities.get(uuid)).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public void save(Room room) {
        if (room == null) {
            throw new EntityNotFoundException();
        }

        entities.put(room.getUuid(), room);
    }

    @Override
    public void update(Room room) {
        if (room == null) {
            throw new EntityNotFoundException();
        }

        entities.put(room.getUuid(), room);
    }
}
