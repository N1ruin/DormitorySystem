package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.infrastructure.loader.UniversityNamesLoader;

import java.util.Random;
import java.util.UUID;

public class UniversityDataGenerator implements EntityDataGenerator {
    private final UniversityRepository universityRepository;
    private final UniversityNamesLoader universityNamesLoader;
    private final Random random = new Random();

    public UniversityDataGenerator(
            UniversityRepository universityRepository, UniversityNamesLoader loader) {
        this.universityRepository = universityRepository;
        this.universityNamesLoader = loader;
    }

    @Override
    public void generateData() {
        String[] universityNames = universityNamesLoader.getUniversityNames();
        for (String universityName : universityNames) {
            String cleanName = universityName.replaceAll("[\\r\\n]+", " ").trim();
            University u = new University(UUID.randomUUID(), cleanName, (byte) random.nextInt(1, 6));
            universityRepository.save(u);
        }
    }
}
