package by.niruin.dormitorySystem.infrastructure.loader;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryRepositoryBase;

public class RepositoryDataLoader {
    private final InMemoryRepositoryBase<User> userRepository;
    private final InMemoryRepositoryBase<University> universityRepository;
    private final InMemoryRepositoryBase<Student> studentRepository;
    private final InMemoryRepositoryBase<Room> roomRepository;
    private final InMemoryRepositoryBase<Dormitory> dormitoryRepository;

    public RepositoryDataLoader(InMemoryRepositoryBase<Dormitory> dormitoryRepository,
                                InMemoryRepositoryBase<User> userRepository,
                                InMemoryRepositoryBase<University> universityRepository,
                                InMemoryRepositoryBase<Student> studentRepository, InMemoryRepositoryBase<Room> roomRepository) {
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

    public void saveData() {
        userRepository.saveAllEntitiesInFile();
        universityRepository.saveAllEntitiesInFile();
        studentRepository.saveAllEntitiesInFile();
        roomRepository.saveAllEntitiesInFile();
        dormitoryRepository.saveAllEntitiesInFile();
    }
}
