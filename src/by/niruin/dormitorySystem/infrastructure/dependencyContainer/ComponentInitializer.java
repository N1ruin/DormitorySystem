package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.exception.AutowiringException;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;
import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class ComponentInitializer {
    private final Logger logger = LoggerFactory.getLogger(ComponentInitializer.class);
    private final ImplementationFinder implementationFinder;

    public ComponentInitializer(ImplementationFinder implementationFinder) {
        this.implementationFinder = implementationFinder;
    }

    public Map<Class<?>, Object> createObjects(Set<Class<?>> classes) {
        logger.info(CREATING_OBJECTS_STARTING_LOG);

        var objects = new HashMap<Class<?>, Object>();

        for (var clazz : classes) {
            var targetClazz = (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()))
                    ? implementationFinder.findImplementationClass(clazz)
                    : clazz;

            getInstance(targetClazz, objects, new HashSet<>());
        }

        logger.info(CREATING_OBJECTS_ENDED_LOG);
        return objects;
    }

    private Object getInstance(Class<?> clazz, Map<Class<?>, Object> objects, Set<Class<?>> triggeredBy) {
        var foundObject = objects.get(clazz);

        if (foundObject != null) {
            return foundObject;
        }

        if (triggeredBy.contains(clazz)) {
            throw new AutowiringException(CIRCULAR_DEPENDENCY_IS_NOT_ALLOWED_ERROR_MESSAGE);
        }

        triggeredBy.add(clazz);

        var constructor = getTargetConstructor(clazz);
        var parameters = getConstructorParameters(constructor, objects, triggeredBy);

        logger.info(CREATING_OBJECT_LOG.formatted(clazz.getSimpleName()));
        return objects.compute(clazz, (k, value) -> getClassInstanceNoEx(constructor, parameters));
    }

    private Constructor<?> getTargetConstructor(Class<?> clazz) {
        var constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new AutowiringException(CLASS_HAS_NO_CONSTRUCTORS_ERROR_MESSAGE.formatted(clazz.getSimpleName()));
        }

        return constructors[0];
    }

    private List<?> getConstructorParameters(Constructor<?> constructor, Map<Class<?>, Object> objects, Set<Class<?>> triggeredBy) {
        if (constructor.getParameters().length == 0) {
            return List.of();
        }

        return Arrays.stream(constructor.getParameters())
                .map(paramClass -> resolveParameter(paramClass, objects, triggeredBy))
                .toList();
    }

    private Object resolveParameter(Parameter parameter, Map<Class<?>, Object> objects, Set<Class<?>> triggeredBy) {
        var parameterType = parameter.getType();
        if (parameterType.isInterface()) {
            var interfaceImpl = implementationFinder.findImplementationClass(parameterType);
            return getInstance(interfaceImpl, objects, triggeredBy);
        }

        return getInstance(parameterType, objects, triggeredBy);
    }

    private Object getClassInstanceNoEx(Constructor<?> constructor, List<?> params) {
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(params.toArray());
        } catch (Exception e) {
            var declaringClassName = constructor.getDeclaringClass()
                    .getName();

            throw new AutowiringException(UNABLE_TO_CREATE_INSTANCE_ERROR_MESSAGE.formatted(declaringClassName));
        }
    }
}
