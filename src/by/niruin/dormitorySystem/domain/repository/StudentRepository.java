package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Student;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StudentRepository extends Repository<Student> {

    List<Student> findByRoomId(UUID roomId);
    Map<UUID, List<Student>> getStudentsInRooms();
    List<String> getStudentNamesWithoutRoom(UUID dormitoryId);
}
