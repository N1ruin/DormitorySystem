package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.infrastructure.loader.RandomFullNameLoader;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class UserDataGenerator implements EntityDataGenerator {
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final Random random = new Random();
    private final RandomFullNameLoader loader;

    public UserDataGenerator(RandomFullNameLoader loader,
                             UserRepository userRepository,
                             UniversityRepository universityRepository,
                             DormitoryRepository dormitoryRepository) {
        this.loader = loader;
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public void generateData() {
        List<University> universities = universityRepository.findAll();
        for (int i = 0; i < universities.size(); i++) {
            UUID universityId = universities.get(i).getId();
            List<Dormitory> dormitoriesInUniversity = dormitoryRepository.findAll().stream()
                    .filter(dormitory -> dormitory.getUniversityId().equals(universityId)).toList();
            if (dormitoriesInUniversity.isEmpty()) {
                continue;
            }
            UUID dormitoryId = dormitoriesInUniversity.get(random.nextInt(0, dormitoriesInUniversity.size())).getId();

            UUID uuid = UUID.randomUUID();
            String login = "testlogin" + i;
            String password = "test" + 100 / (i + 1) + "password" + i;
            Role[] roles = Role.values();
            Role role = roles[random.nextInt(4)];
            FullName fullName = loader.generate();
            Gender[] genders = Gender.values();
            Gender gender = genders[random.nextInt(2)];

            User user = new User(uuid, login, password, role, fullName.getFirstName(), fullName.getLastName(),
                    fullName.getFatherName(), gender, universityId, dormitoryId);
            userRepository.save(user);
        }
    }
}
