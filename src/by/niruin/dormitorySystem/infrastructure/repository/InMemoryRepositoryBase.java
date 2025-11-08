package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.repository.Identity;
import by.niruin.dormitorySystem.domain.repository.Repository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class InMemoryRepositoryBase<T extends Identity<ID>, ID> implements Repository<T, ID> {
    protected final Map<ID, T> entities = new HashMap<>();
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

    public void loadAllEntitiesFromFile() {
        try {
            String usersData = FileUtil.readString(filePath);
            mapper.mapStringToEntities(usersData).forEach(entity -> entities.put(entity.getId(), entity));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(ID id) {
        if (id == null || !entities.containsKey(id)) {
            throw new EntityNotFoundException(id);
        }
        entities.remove(id);
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public T findById(ID id) {
        return Optional.of(id)
                .map(entities::get)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void save(T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        entities.put(entity.getId(), entity);
    }

    @Override
    public void update(T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        entities.put(entity.getId(), entity);
    }
}
