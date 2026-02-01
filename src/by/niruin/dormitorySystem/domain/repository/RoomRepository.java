package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Room;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends Repository<Room> {

    List<Room> findByDormitoryId(UUID dormitoryId);

    List<Room> findAllOrderBy(Comparator<Room> comparator);

    Optional<Room> findByNumber(int number);

    List<Room> findByCurrentDormitoryId();
}
