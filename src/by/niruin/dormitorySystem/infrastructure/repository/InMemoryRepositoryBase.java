package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class InMemoryRepositoryBase<T> {
    protected final Map<UUID, T> entities = new HashMap<>();
    protected final EntityMapper<T> mapper;
    protected final Path filePath;
    protected Function<T, UUID> uuidExtractor;

    public InMemoryRepositoryBase(EntityMapper<T> mapper, Path filePath, Function<T, UUID> uuidExtractor) {
        this.mapper = mapper;
        this.filePath = filePath;
        this.uuidExtractor = uuidExtractor;
    }

    public void saveAllEntitiesInFile() {
        String usersData = mapper.mapEntitiesToString(entities.values());
        FileUtil.writeString(filePath, usersData);
    }

    public void loadAllEntitiesFromFile() {
        try {
            String usersData = FileUtil.readString(filePath);
            Collection<T> entityCollection = mapper.mapStringToEntities(usersData);

            entityCollection.forEach(entity -> entities.put(uuidExtractor.apply(entity), entity));

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
