package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import by.niruin.dormitorySystem.infrastructure.mapper.RoomMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryRoomRepository implements RoomRepository {
    private final Map<UUID, Room> rooms = new HashMap<>();
    private final RoomMapper mapper;
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/room.txt");

    public InMemoryRoomRepository(RoomMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void persistRooms() {
        String roomsData = mapper.mapRoomsToString(rooms.values());
        FileUtil.writeString(ROOMS_FILE_PATH, roomsData);
    }

    @Override
    public void fetchRooms() {
        String roomsData = FileUtil.readString(ROOMS_FILE_PATH);
        mapper.mapStringToRooms(roomsData)
                .forEach(room -> rooms.put(room.getId(), room));

    }

    @Override
    public void save(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public List<Room> findAll() {
        return List.copyOf(rooms.values());
    }

    @Override
    public void update(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public void delete(UUID id) {
        rooms.remove(id);
    }

    @Override
    public List<Room> findByDormitoryId(UUID dormitoryId) {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(dormitoryId))
                .toList();
    }

    @Override
    public Optional<Room> findById(UUID id) {
        return rooms.values().stream()
                .filter(room -> room.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Room> findAllByDormitoryIdOrderBy(UUID dormitoryId, Comparator<Room> comparator) {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(dormitoryId))
                .sorted(comparator)
                .toList();
    }

    @Override
    public Optional<Room> findByNumber(UUID dormitoryId, int number) {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(dormitoryId))
                .filter(room -> room.getNumber() == number)
                .findFirst();
    }
}
