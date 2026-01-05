package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Student;

import java.util.Collection;

public interface StudentMapper {
    String mapStudentsToString(Collection<Student> students);

    Collection<Student> mapStringToStudents(String studentsData);
}
