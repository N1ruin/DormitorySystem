package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryStudentRepository extends InMemoryRepositoryBase<Student> implements StudentRepository {
    public static final Path STUDENTS_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryStudentRepository(EntityMapper<Student> studentMapper) {
        super(studentMapper, STUDENTS_FILE_PATH, Student::getUuid);
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid == null || !entities.containsKey(uuid)) {
            throw new EntityNotFoundException(uuid);
        }
        entities.remove(uuid);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Student findById(UUID uuid) {
        return Optional.ofNullable(entities.get(uuid)).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public void save(Student student) {
        if (student == null) {
            throw new EntityNotFoundException();
        }

        entities.put(student.getUuid(), student);
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new EntityNotFoundException();
        }

        entities.put(student.getUuid(), student);
    }
}
