package by.niruin.dormitorySystem.infrastructure.dependencyContainer;

import by.niruin.dormitorySystem.infrastructure.annotation.Autowired;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentInitializer {

    public Map<Class<?>, Object> initObjects(List<Class<?>> sortedComponentList) {
        Map<Class<?>, Object> container = new HashMap<>();

        for (var clazz : sortedComponentList) {
            Object obj = createObject(clazz, container);
            container.put(clazz, obj);
        }
        return container;
    }

    private Object createObject(Class<?> clazz, Map<Class<?>, Object> container) {
        for (var constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                return processConstructor(constructor, container);
            }
        }
        return processConstructor(clazz.getDeclaredConstructors()[0], container);
    }

    private Object processConstructor(Constructor<?> constructor, Map<Class<?>, Object> container) {
        try {
            Object[] parameters = processParamethers(constructor, container);
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //log
            throw new RuntimeException(e);
        }
    }

    private Object[] processParamethers(Constructor<?> constructor, Map<Class<?>, Object> container) {
        Parameter[] parameters = constructor.getParameters();
        Object[] objects = new Object[parameters.length];

        for (int i = 0; i < objects.length; i++) {
            objects[i] = getParameterObject(parameters[i], container);
        }
        return objects;
    }

    private Object getParameterObject(Parameter parameter, Map<Class<?>, Object> container) {
        Class<?> targetClass = parameter.isAnnotationPresent(Qualifier.class)
                ? parameter.getAnnotation(Qualifier.class).value()
                : parameter.getType();

        return container.get(targetClass);
    }
}
