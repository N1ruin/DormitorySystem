package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import by.niruin.dormitorySystem.infrastructure.mapper.RoomMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryRoomRepository implements RoomRepository {
    private final Map<UUID, Room> rooms = new HashMap<>();
    private final RoomMapper mapper;
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/room.txt");
    private UUID currentDormitoryId;

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
        try {
            String roomsData = FileUtil.readString(ROOMS_FILE_PATH);
            mapper.mapStringToRooms(roomsData)
                    .forEach(room -> rooms.put(room.getId(), room));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
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
        if (id == null || rooms.remove(id) == null) {
            throw new EntityNotFoundException(id, Room.class);
        }

        rooms.remove(id);
    }

    @Override
    public List<Room> findAllOrderBy(Comparator<Room> comparator) {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(getCurrentDormitoryId()))
                .sorted(comparator)
                .toList();
    }

    @Override
    public Optional<Room> findByNumber(int number) {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(getCurrentDormitoryId()))
                .filter(room -> room.getNumber() == number)
                .findFirst();
    }

    @Override
    public List<Room> findByCurrentDormitoryId() {
        return rooms.values().stream()
                .filter(room -> room.getDormitoryId().equals(getCurrentDormitoryId()))
                .toList();
    }

    private UUID getCurrentDormitoryId() {
        if (currentDormitoryId == null) {
            currentDormitoryId = ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
        }
        return currentDormitoryId;
    }
}
