package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DormitoryDataGenerator implements EntityDataGenerator {
    public static final int MAX_DORMITORY_IN_UNIVERSITY = 10;
    public static final int MIN_DORMITORY_IN_UNIVERSITY = 4;
    public static final int MIN_ROOMS_IN_DORMITORY = 30;
    public static final int MAX_ROOMS_IN_DORMITORY = 50;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final Random random = new Random();

    public DormitoryDataGenerator(UniversityRepository universityRepository,
                                  DormitoryRepository dormitoryRepository) {
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public void generateData() {
        List<University> universityList = universityRepository.findAll();
        for (University university : universityList) {
            UUID universityId = university.getId();
            for (int j = 1; j < random.nextInt(MIN_DORMITORY_IN_UNIVERSITY, MAX_DORMITORY_IN_UNIVERSITY); j++) {
                UUID uuid = UUID.randomUUID();
                int roomsCount = random.nextInt(MIN_ROOMS_IN_DORMITORY, MAX_ROOMS_IN_DORMITORY);
                boolean availableForLiving = random.nextInt(100) % 4 != 0;
                dormitoryRepository.save(new Dormitory(uuid, j, roomsCount, universityId, availableForLiving));
            }
        }
    }
}
