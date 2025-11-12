package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import java.util.*;

public class DependencyContainer {
    private final Map<Class<?>, Object> container;
    private final PackageScanner packageScanner = new PackageScanner();
    private final DependenciesLoadingOrderBuilder builder = new DependenciesLoadingOrderBuilder();
    private final ComponentInitializer componentInitializer = new ComponentInitializer();

    public DependencyContainer(String packageName) {
        Set<Class<?>> findedComponents = packageScanner.scanPackage(packageName);
        List<Class<?>> dependenciesLoadingOrder = builder.getDependenciesOrder(findedComponents);
        container = componentInitializer.initObjects(dependenciesLoadingOrder);
    }

    public <T> T getObject(Class<?> clazz) {
        if (!container.containsKey(clazz)) {
            throw new RuntimeException();
        }
        return (T) container.get(clazz);
    }
}
