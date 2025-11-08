package by.niruin.dormitorySystem.infrastructure.loader;

import by.niruin.dormitorySystem.infrastructure.repository.*;

public class RepositoryDataLoader {
    private final InMemoryUserRepository userRepository;
    private final InMemoryUniversityRepository universityRepository;
    private final InMemoryStudentRepository studentRepository;
    private final InMemoryRoomRepository roomRepository;
    private final InMemoryDormitoryRepository dormitoryRepository;

    public RepositoryDataLoader(InMemoryDormitoryRepository dormitoryRepository,
                                InMemoryUserRepository userRepository,
                                InMemoryUniversityRepository universityRepository,
                                InMemoryStudentRepository studentRepository, InMemoryRoomRepository roomRepository) {
        this.dormitoryRepository = dormitoryRepository;
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    public void loadData() {
        userRepository.loadAllEntitiesFromFile();
        universityRepository.loadAllEntitiesFromFile();
        studentRepository.loadAllEntitiesFromFile();
        roomRepository.loadAllEntitiesFromFile();
        dormitoryRepository.loadAllEntitiesFromFile();
    }

    public void persistData() {
        userRepository.persistEntities();
        universityRepository.persistEntities();
        studentRepository.persistEntities();
        roomRepository.persistEntities();
        dormitoryRepository.persistEntities();
    }
}
