package by.niruin.dormitorySystem.infrastructure.loader;

import by.niruin.dormitorySystem.domain.repository.*;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

@Component
public class RepositoryDataLoader {
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;

    public RepositoryDataLoader(UserRepository userRepository, UniversityRepository universityRepository,
                                StudentRepository studentRepository, RoomRepository roomRepository,
                                DormitoryRepository dormitoryRepository) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    public void loadData() {
        userRepository.fetchUsers();
        universityRepository.fetchUniversities();
        studentRepository.fetchStudents();
        roomRepository.fetchRooms();
        dormitoryRepository.fetchDormitories();
    }

    public void persistData() {
        userRepository.persistUsers();
        universityRepository.persistUniversities();
        studentRepository.persistStudents();
        roomRepository.persistRooms();
        dormitoryRepository.persistDormitories();
    }
}
