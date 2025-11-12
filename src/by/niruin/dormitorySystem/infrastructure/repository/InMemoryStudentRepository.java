package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.StudentMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryStudentRepository extends InMemoryRepositoryBase<Student<UUID>, UUID> {
    public static final Path STUDENTS_FILE_PATH = Paths.get("./resources/entity/student.txt");

    public InMemoryStudentRepository(@Qualifier(StudentMapper.class) EntityMapper<Student<UUID>> studentMapper) {
        super(studentMapper, STUDENTS_FILE_PATH);
    }
}
