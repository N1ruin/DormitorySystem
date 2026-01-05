package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentInitializer {
    private final Logger logger = LoggerFactory.getLogger(ComponentInitializer.class);

    public Map<Class<?>, Object> initObjects(List<Class<?>> sortedComponentList) {
        Map<Class<?>, Object> container = new HashMap<>();

        for (var clazz : sortedComponentList) {
            Object obj = createObject(clazz, container);
            container.put(clazz, obj);
        }
        return container;
    }

    private Object createObject(Class<?> clazz, Map<Class<?>, Object> container) {
        return processConstructor(clazz.getConstructors()[0], container);
    }

    private Object processConstructor(Constructor<?> constructor, Map<Class<?>, Object> container) {
        try {
            Object[] parameters = processParameters(constructor, container);
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    private Object[] processParameters(Constructor<?> constructor, Map<Class<?>, Object> container) {
        Parameter[] parameters = constructor.getParameters();
        Object[] objects = new Object[parameters.length];

        for (int i = 0; i < objects.length; i++) {
            objects[i] = getParameterObject(parameters[i], container);
        }
        return objects;
    }

    //TODO доработать чтобы искал подходящий класс под интерфейс.
    private Object getParameterObject(Parameter parameter, Map<Class<?>, Object> container) {
        Class<?> targetClass = parameter.isAnnotationPresent(Qualifier.class)
                ? parameter.getAnnotation(Qualifier.class).value()
                : parameter.getType();

        return container.get(targetClass);
    }
}
