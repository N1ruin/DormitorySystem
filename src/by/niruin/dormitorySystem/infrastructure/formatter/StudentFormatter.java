package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.student.StudentInfoDto;

import java.util.List;

public interface StudentFormatter {
    String formatStudentsToStudentsInfo(StudentInfoDto... dtos);

    String formatStudentNamesToNumeredNames(List<String> names, String title);
}
