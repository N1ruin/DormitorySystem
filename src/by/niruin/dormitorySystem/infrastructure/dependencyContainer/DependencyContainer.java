package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import java.util.*;

public class DependencyContainer {
    private final Map<Class<?>, Object> container;

    public DependencyContainer(String packageName) {
        PackageScanner packageScanner1 = new PackageScanner();
        Set<Class<?>> classes = packageScanner1.scanPackage(packageName);
        ComponentInitializer componentInitializer = new ComponentInitializer(new ImplementationFinder(classes));
        container = componentInitializer.createObjects(classes);
    }

    public <T> T getObject(Class<?> clazz) {
        if (!container.containsKey(clazz)) {
            throw new RuntimeException();
        }
        return (T) container.get(clazz);
    }
}
