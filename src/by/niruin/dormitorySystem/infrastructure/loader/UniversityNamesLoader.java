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
    private String[] universityNames;

    public UniversityNamesLoader() {
        loadNames();
    }

    public void loadNames() {
        universityNames = FileUtil.readString(Path.of(FIRST_NAME_FILE_PATH)).split("\n");
    }

    public String[] getUniversityNames() {
        return universityNames;
    }
}
