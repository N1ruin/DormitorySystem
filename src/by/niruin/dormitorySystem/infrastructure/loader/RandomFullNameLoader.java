package by.niruin.dormitorySystem.infrastructure.loader;

import by.niruin.dormitorySystem.domain.model.FullName;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.util.FileUtil;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

@Component
public class RandomFullNameLoader {
    public static final String FIRST_NAME_FILE_PATH = "./resources/dataGenerator/firstNameSet";
    public static final String LAST_NAME_FILE_PATH = "./resources/dataGenerator/lastNameSet";
    public static final String FATHER_NAME_FILE_PATH = "./resources/dataGenerator/fatherNameSet";
    private final Random random = new Random();
    private String[] firstNames;
    private String[] lastNames;
    private String[] fatherNames;

    public RandomFullNameLoader() {
        loadNames();
    }

    public void loadNames() {
        String firstNameLines = FileUtil.readString(Path.of(FIRST_NAME_FILE_PATH));
        String lastNameLines = FileUtil.readString(Path.of(LAST_NAME_FILE_PATH));
        String fatherNameLines = FileUtil.readString(Path.of(FATHER_NAME_FILE_PATH));

        firstNames = cleanAndSplit(firstNameLines);
        lastNames = cleanAndSplit(lastNameLines);
        fatherNames = cleanAndSplit(fatherNameLines);

    }

    private String[] cleanAndSplit(String content) {
        if (content == null || content.isEmpty()) {
            return new String[0];
        }

        return Arrays.stream(content.split("\n"))
                .map(name -> name.replaceAll("[\\r\\n]+", " ").trim())
                .filter(name -> !name.isEmpty())
                .toArray(String[]::new);
    }

    public FullName generate() {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String fatherName = fatherNames[random.nextInt(fatherNames.length)];

        return new FullName(firstName, fatherName, lastName);
    }

}
