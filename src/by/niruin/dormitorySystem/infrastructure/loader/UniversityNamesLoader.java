package by.niruin.dormitorySystem.infrastructure.loader;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Arrays;

@Component
public class UniversityNamesLoader {
    public static final String FIRST_NAME_FILE_PATH = "./resources/dataGenerator/universityNameSet.txt";
    private final Logger logger = LoggerFactory.getLogger(UniversityNamesLoader.class);
    private String[] universityNames;

    public UniversityNamesLoader() {
        loadNames();
    }

    public void loadNames() {
        try {
            universityNames = FileUtil.readString(Path.of(FIRST_NAME_FILE_PATH)).split("\n");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public String[] getUniversityNames() {
        return universityNames;
    }
}
