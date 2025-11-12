package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractEntityMapper<T extends Identity<?>> implements EntityMapper<T> {
    public static final String FIELDS_DELIMITER = ",";
    public static final String NEW_LINE_SYMBOL = "\n";
    public static final String NOT_VALID_FIELDS_QUANTITY_MESSAGE = "Fields quantity in object not equals quantity parsed fields";
    public static final String ENTITIES_ID_FIELD_NAME = "id";

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

    protected Object getIdObject(Class<?> entityClass, String fieldValue) {
        try {
            Class<?> idType = entityClass.getDeclaredField(ENTITIES_ID_FIELD_NAME).getType();
            Object id;

            if (idType.equals(UUID.class)) {
                id = UUID.fromString(fieldValue);
            } else if (idType.equals(Integer.TYPE)) {
                id = Integer.parseInt(fieldValue);
            } else {
                throw new RuntimeException("Unsupported id class");
            }

            return id;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
