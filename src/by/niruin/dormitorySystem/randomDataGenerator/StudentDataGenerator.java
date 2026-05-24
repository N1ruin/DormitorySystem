package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.infrastructure.loader.RandomFullNameLoader;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StudentDataGenerator implements EntityDataGenerator {
    public static final int MIN_YEAR_OF_ENTERING = 2023;
    public static final int MAX_YEAR_OF_ENTERING = 2024;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UniversityRepository universityRepository;
    private final RandomFullNameLoader randomFullNameLoader;
    private final Random random = new Random();

    public StudentDataGenerator(StudentRepository studentRepository,
                                RoomRepository roomRepository,
                                DormitoryRepository dormitoryRepository,
                                UniversityRepository universityRepository,
                                RandomFullNameLoader randomFullNameLoader) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.universityRepository = universityRepository;
        this.randomFullNameLoader = randomFullNameLoader;
    }

    @Override
    public void generateData() {
        List<University> universityList = universityRepository.findAll();

        for (int i = 0; i < universityList.size(); i++) {
            UUID id = UUID.randomUUID();
            FullName fullName = randomFullNameLoader.generate();
            //TODO добавить рандомных студенток для тестирования валидации комнат по половому признаку
            UUID universityId = universityList.get(random.nextInt(0, universityList.size())).getId();
            List<Dormitory> dormitoryList = dormitoryRepository.findByUniversityId(universityId);
            UUID dormitoryId;
            UUID roomId;
            if (dormitoryList.isEmpty()) {
                dormitoryId = null;
                roomId = null;
            } else {
                dormitoryId = dormitoryList.get(random.nextInt(0, dormitoryList.size())).getId();
                List<Room> roomList = roomRepository.findAll()
                        .stream()
                        .filter(room -> room.getDormitoryId().equals(dormitoryId))
                        .toList();
                roomId = roomList.get(random.nextInt(roomList.size())).getId();
            }
            int yearEntering = random.nextInt(MIN_YEAR_OF_ENTERING, MAX_YEAR_OF_ENTERING);
            Month month = Month.of(random.nextInt(1, 13));
            int dayOfMonth = random.nextInt(1, month.maxLength());
            LocalDate dateOfEntering = LocalDate.of(yearEntering, month, dayOfMonth);

            Student student = new Student(id, fullName, Gender.MALE, universityId, roomId, dormitoryId,
                    dateOfEntering, null, dateOfEntering, null);
            studentRepository.save(student);
        }
    }
}
