package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class RoomDataGenerator implements EntityDataGenerator {
    public static final int MIN_ROOM_CAPACITY = 1;
    public static final int MAX_ROOM_CAPACITY = 5;
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;
    private final Random random = new Random();

    public RoomDataGenerator(RoomRepository roomRepository,
                             DormitoryRepository dormitoryRepository) {
        this.roomRepository = roomRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public void generateData() {
        List<Dormitory> dormitoryList = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitoryList) {
            for (int i = 1; i <= dormitory.getRoomsCount(); i++) {
                UUID id = UUID.randomUUID();
                byte capacity = (byte) random.nextInt(MIN_ROOM_CAPACITY, MAX_ROOM_CAPACITY);
                boolean availableForLiving = i % 4 != 0;
                boolean isMaleOnly = i % 3 != 0;
                Room room = new Room(id, capacity, i, availableForLiving, isMaleOnly, dormitory.getId());
                roomRepository.save(room);
            }
        }
    }
}
