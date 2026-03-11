package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.exception.AutowiringException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.FOUND_MANY_IMPL_ERROR_MESSAGE;
import static by.niruin.dormitorySystem.constant.ConsoleMessage.IMPL_NOT_FOUND_ERROR_MESSAGE;

public class ImplementationFinder {
    private final Set<Class<?>> loadedClasses;

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
            throw new AutowiringException(IMPL_NOT_FOUND_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
        }

        if (implementations.size() > 1) {
            throw new AutowiringException(FOUND_MANY_IMPL_ERROR_MESSAGE.formatted(intefraceClass.getSimpleName()));
        }

        return implementations.getFirst();
    }
}
