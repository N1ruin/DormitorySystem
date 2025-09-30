package by.niruin.dormitorySystem.infrastructure.mapper;

import java.util.Collection;

public interface EntityMapper<T> {

    String mapEntitiesToString(Collection<T> objects);

    Collection<T> mapStringToEntities(String fields);
}
