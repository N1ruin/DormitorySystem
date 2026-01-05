package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.DETECTED_CYCLIC_DEPENDENCY_ERROR_MESSAGE;
import static by.niruin.dormitorySystem.constant.LoggerMessage.COMPONENT_ADDED_TO_LOADING_ORDER_LOG;

public class DependenciesLoadingOrderBuilder {
    private final Logger logger = LoggerFactory.getLogger(DependenciesLoadingOrderBuilder.class);

    public List<Class<?>> getDependenciesOrder(Set<Class<?>> classes) {
        Map<Class<?>, Set<Class<?>>> dependeciesMap = new HashMap<>();

        for (Class<?> clazz : classes) {
            addClassDependencies(dependeciesMap, clazz);
        }

        return sortDependencies(dependeciesMap);
    }

    private void addClassDependencies(Map<Class<?>, Set<Class<?>>> dependenciesMap, Class<?> target) {
        Constructor<?> constructor = target.getConstructors()[0];
        Parameter[] parameters = constructor.getParameters();
        Set<Class<?>> targetClassDependencies = new HashSet<>();

        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(Qualifier.class)) {
                Qualifier qualifier = parameter.getAnnotation(Qualifier.class);
                targetClassDependencies.add(qualifier.value());
            } else {
                targetClassDependencies.add(parameter.getType());
            }
        }
        dependenciesMap.put(target, targetClassDependencies);
    }
    //todo пересмотреть алгоритм
    private List<Class<?>> sortDependencies(Map<Class<?>, Set<Class<?>>> dependencies) {
        List<Class<?>> dependenciesLoadingOrder = new ArrayList<>();

        while (!dependencies.isEmpty()) {
            List<Class<?>> readyClasses = new ArrayList<>();
            for (var clazz : dependencies.keySet()) {
                if (dependencies.get(clazz).isEmpty()) {
                    readyClasses.add(clazz);
                }
            }

            if (readyClasses.isEmpty()) {
                throw new RuntimeException(DETECTED_CYCLIC_DEPENDENCY_ERROR_MESSAGE);
            }

            for (Class<?> clazz : readyClasses) {
                dependenciesLoadingOrder.add(clazz);
                dependencies.remove(clazz);
                for (Set<Class<?>> dependenciesSet : dependencies.values()) {
                    dependenciesSet.remove(clazz);
                }
                logger.info(COMPONENT_ADDED_TO_LOADING_ORDER_LOG.formatted(clazz.getSimpleName()));
            }
        }
        return dependenciesLoadingOrder;
    }
}
