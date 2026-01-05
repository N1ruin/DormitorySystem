package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.exception.AutowiringException;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.FOUND_MANY_IMPL_ERROR_MESSAGE;
import static by.niruin.dormitorySystem.constant.ConsoleMessage.IMPL_NOT_FOUND_ERROR_MESSAGE;

public class ImplementationFinder {
    private final Set<Class<?>> loadedClasses;
    private final Logger logger = LoggerFactory.getLogger(ImplementationFinder.class);

    public ImplementationFinder(Set<Class<?>> loadedClasses) {
        this.loadedClasses = loadedClasses;
    }

    public Class<?> findImplementationClass(Class<?> intefraceClass) {
        List<Class<?>> implementations = new ArrayList<>();
        for (Class<?> clazz : loadedClasses) {
            if (!clazz.isInterface() && intefraceClass.isAssignableFrom(clazz)) {
                implementations.add(clazz);
            }
        }

        if (implementations.isEmpty()) {
            logger.error(IMPL_NOT_FOUND_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
            throw new AutowiringException(IMPL_NOT_FOUND_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
        }

        if (implementations.size() > 1) {
            logger.error(FOUND_MANY_IMPL_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
            throw new AutowiringException(FOUND_MANY_IMPL_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
        }

        return implementations.getFirst();
    }
}
