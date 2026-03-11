package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Student;

import java.util.*;

public interface StudentRepository {
    void persistStudents();

    void fetchStudents();

    void save(Student student);

    List<Student> findAll();

    void update(Student student);

    void delete(UUID uuid);

    List<Student> findByUniversityId(UUID universityId);

    List<Student> findAllByUniversityIdOrderBy(UUID universityId, Comparator<Student> comparator);

    Map<UUID, List<Student>> findByDormitoryIdGroupingByRoomId(UUID dormitoryId, UUID roomId);

    List<Student> findByDormitoryId(UUID dormitoryId);

    Optional<Student> findByUniversityIdAndFullName(UUID universityId, String fullName);

    List<Student> findByRoomId(UUID roomId);
}
