package by.niruin.dormitorySystem.infrastructure.mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class AbstractEntityMapper<T> implements EntityMapper<T> {
    public static final String FIELDS_DELIMITER = ",";
    public static final String NEW_LINE_SYMBOL = "\n";

    protected abstract int getFieldsCount();

    @Override
    public String mapEntitiesToString(Collection<T> objects) {
        return objects.stream()
                .map(this::entityFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    protected abstract String entityFieldsToString(T entity);


    @Override
    public Collection<T> mapStringToEntities(String fields) {
        if (fields == null || fields.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String[] dormitoryFields = fields.split(NEW_LINE_SYMBOL);

        return Arrays.stream(dormitoryFields)
                .map(this::mapStringToEntity)
                .toList();
    }

    protected abstract T mapStringToEntity(String fields);
}
