package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractEntityMapper<T extends Identity> implements EntityMapper<T> {
    public static final String FIELDS_DELIMITER = ",";
    public static final String NEW_LINE_SYMBOL = "\n";
    public static final String NULL_STRING = "null";
    public static final int ADDITIONAL_FIELDS_FOR_FULLNAME = 2;

    @Override
    public String mapEntitiesToString(Collection<T> entities) {
        return entities.stream()
                .map(this::entityFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    protected abstract String entityFieldsToString(T entity);

    @Override
    public Collection<T> mapStringToEntities(String fields) {
        if (fields == null || fields.trim().isEmpty()) {
            return List.of();
        }

        String[] dormitoryFields = fields.split(NEW_LINE_SYMBOL);

        return Arrays.stream(dormitoryFields)
                .map(this::mapStringToEntity)
                .toList();
    }

    protected abstract T mapStringToEntity(String fields);
}
