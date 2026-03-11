package by.niruin.dormitorySystem.domain.model.dto.student;

import by.niruin.dormitorySystem.domain.model.Gender;

import java.time.LocalDate;

public record CreateStudentDto(String firstName,
                               String lastName,
                               String fatherName,
                               Gender gender,
                               LocalDate enteringDate) {
}
