package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.repository.Identity;
import by.niruin.dormitorySystem.domain.repository.Repository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;

public class InMemoryRepositoryBase<T extends Identity> implements Repository<T> {
    protected final Map<UUID, T> entities = new HashMap<>();
    protected final EntityMapper<T> mapper;
    protected final Path filePath;

    public InMemoryRepositoryBase(EntityMapper<T> mapper, Path filePath) {
        this.mapper = mapper;
        this.filePath = filePath;
    }

    public void persistEntities() {
        String usersData = mapper.mapEntitiesToString(entities.values());
        FileUtil.writeString(filePath, usersData);
    }

    public void fetchEntities() {
        try {
            String usersData = FileUtil.readString(filePath);
            mapper.mapStringToEntities(usersData)
                    .forEach(entity -> entities.put(entity.getId(), entity));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(UUID id) {
        if (id == null || !entities.containsKey(id)) {
            Type entityType = this.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) entityType;
            Class<?> genericClass =(Class<?>) parameterizedType.getActualTypeArguments()[0];
            throw new EntityNotFoundException(id, genericClass);
        }
        entities.remove(id);
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.of(id)
                .map(entities::get);
    }

    @Override
    public void save(T entity) {
        Objects.requireNonNull(entity);
        entities.put(entity.getId(), entity);
    }

    @Override
    public void update(T entity) {
        Objects.requireNonNull(entity);
        entities.put(entity.getId(), entity);
    }
}
