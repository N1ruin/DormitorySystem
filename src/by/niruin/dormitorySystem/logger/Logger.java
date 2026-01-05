package by.niruin.dormitorySystem.logger;

import by.niruin.dormitorySystem.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.CREATING_DIRECTORY_ERROR_MESSAGE;

public class Logger {
    public static final String LOG_FILES_PATH = "./logs";
    public static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static final DateTimeFormatter FILE_TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH-mm-ss");
    private static final String logFileName;
    private final Class<?> clazz;

    static {
        String startAppDateTime = LocalDateTime.now().format(FILE_TIME_PATTERN);
        logFileName = "app-" + startAppDateTime + ".log";
        Path directory = Path.of(LOG_FILES_PATH);
        try {
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
        } catch (Exception e) {
            System.out.println(CREATING_DIRECTORY_ERROR_MESSAGE.formatted(directory.toString()));
        }
    }

    public Logger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private void log(LogLevel level, String message) {
        StringBuilder logMessage = new StringBuilder();
        String dateTime = LocalDateTime.now().format(DATE_TIME_PATTERN);
        logMessage.append("[").append(dateTime).append("] ");
        logMessage.append("[").append(level.name()).append("] ");
        logMessage.append("[").append(clazz.getSimpleName()).append("]: ");
        logMessage.append(message).append("\n");

        String logFilePath = "%s/%s".formatted(LOG_FILES_PATH, logFileName);
        FileUtil.appendString(Path.of(logFilePath), logMessage.toString());
    }
}
