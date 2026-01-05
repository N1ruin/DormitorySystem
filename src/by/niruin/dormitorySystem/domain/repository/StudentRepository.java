package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Student;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StudentRepository {
    void persistStudents();

    void fetchStudents();

    void save(Student student);

    List<Student> findAll();

    void update(Student student);

    void delete(UUID uuid);

    Map<UUID, List<Student>> getStudentsInRooms();

    List<String> getStudentNamesWithoutRoom(UUID dormitoryId);
}
