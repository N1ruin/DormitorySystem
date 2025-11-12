package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Autowired;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

public class DependenciesLoadingOrderBuilder {
    public List<Class<?>> getDependenciesOrder(Set<Class<?>> classes) {
        Map<Class<?>, Set<Class<?>>> dependeciesMap = new HashMap<>();

        for (Class<?> clazz : classes) {
            addClassDependencies(dependeciesMap, clazz);
        }

        return sortDependencies(dependeciesMap);
    }

    private void addClassDependencies(Map<Class<?>, Set<Class<?>>> dependenciesMap, Class<?> target) {
        Constructor<?> constructor = getTargetConstructor(target);
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

    private Constructor<?> getTargetConstructor(Class<?> clazz) {
        Constructor<?> target = null;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                target = constructor;
                break;
            }
        }

        if (target == null) {
            target = clazz.getDeclaredConstructors()[0];
        }
        return target;
    }

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
                throw new RuntimeException("Find cyclic dependency!");
            }

            for (Class<?> clazz : readyClasses) {
                dependenciesLoadingOrder.add(clazz);
                dependencies.remove(clazz);
                for (Set<Class<?>> dependenciesSet : dependencies.values()) {
                    dependenciesSet.remove(clazz);
                }
            }
        }
        return dependenciesLoadingOrder;
    }
}
