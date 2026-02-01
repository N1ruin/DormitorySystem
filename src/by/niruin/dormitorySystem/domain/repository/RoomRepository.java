package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Room;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    void persistRooms();

    void fetchRooms();

    void save(Room entity);

    List<Room> findAll();

    void update(Room entity);

    void delete(UUID uuid);

    List<Room> findAllByDormitoryIdOrderBy(UUID dormitoryId, Comparator<Room> comparator);

    Optional<Room> findByNumber(UUID dormitoryId, int number);

    List<Room> findByDormitoryId(UUID dormitoryId);

    Optional<Room> findById(UUID id);
}
