package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.RoomMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryRoomRepository extends InMemoryRepositoryBase<Room> implements RoomRepository {
    public static final Path ROOMS_FILE_PATH = Paths.get("./resources/entity/room.txt");
    private UUID currentDormitoryId;

    public InMemoryRoomRepository(@Qualifier(value = RoomMapper.class) EntityMapper<Room> roomMapper) {
        super(roomMapper, ROOMS_FILE_PATH);
    }

    @Override
    public List<Room> findByDormitoryId(UUID dormitoryId) {//todo переписать под активную общагу (перепишется после удаления базового класса и выноса все в свои классы
        return entities.values().stream()
                .filter(room -> room.getDormitoryId().equals(dormitoryId))
                .toList();
    }

    @Override
    public List<Room> findAllOrderBy(Comparator<Room> comparator) {
        return entities.values().stream()
                .filter(room -> room.getDormitoryId().equals(getCurrentDormitoryId()))
                .sorted(comparator)
                .toList();
    }

    @Override
    public Optional<Room> findByNumber(int number) {
        return entities.values().stream()
                .filter(room -> room.getDormitoryId().equals(getCurrentDormitoryId()))
                .filter(room -> room.getNumber() == number)
                .findFirst();
    }

    @Override
    public List<Room> findByCurrentDormitoryId() {
        return entities.values().stream()
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
