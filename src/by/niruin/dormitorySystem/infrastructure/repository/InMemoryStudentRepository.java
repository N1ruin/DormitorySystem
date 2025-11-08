package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryStudentRepository extends InMemoryRepositoryBase<Student<UUID>, UUID> {
    public static final Path STUDENTS_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryStudentRepository(EntityMapper<Student<UUID>> studentMapper) {
        super(studentMapper, STUDENTS_FILE_PATH);
    }
}
