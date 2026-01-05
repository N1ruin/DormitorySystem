package by.niruin.dormitorySystem.logger;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;

@Component
public class LoggerFactory {
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }
}
