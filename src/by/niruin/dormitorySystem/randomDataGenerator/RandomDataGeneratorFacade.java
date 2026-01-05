package by.niruin.dormitorySystem.randomDataGenerator;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;

@Component
public class RandomDataGeneratorFacade {
    public static final String USERS_DATA_FILE_PATH = "./resources/entity/user.txt";
    private final UniversityDataGenerator universityDataGenerator;
    private final DormitoryDataGenerator dormitoryDataGenerator;
    private final RoomDataGenerator roomDataGenerator;
    private final StudentDataGenerator studentDataGenerator;
    private final UserDataGenerator userDataGenerator;

    public RandomDataGeneratorFacade(UniversityDataGenerator universityDataGenerator,
                                     DormitoryDataGenerator dormitoryDataGenerator, RoomDataGenerator roomDataGenerator,
                                     StudentDataGenerator studentDataGenerator, UserDataGenerator userDataGenerator) {
        this.universityDataGenerator = universityDataGenerator;
        this.dormitoryDataGenerator = dormitoryDataGenerator;
        this.roomDataGenerator = roomDataGenerator;
        this.studentDataGenerator = studentDataGenerator;
        this.userDataGenerator = userDataGenerator;
    }

    public void generateTestData() throws FileNotFoundException {
        if (FileUtil.readString(Path.of(USERS_DATA_FILE_PATH)).isBlank()) {
            universityDataGenerator.generateData();
            dormitoryDataGenerator.generateData();
            roomDataGenerator.generateData();
            studentDataGenerator.generateData();
            userDataGenerator.generateData();
        }
    }
}
