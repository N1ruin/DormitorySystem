package by.niruin.dormitorySystem.domain.model.dto.student;

import by.niruin.dormitorySystem.domain.model.Gender;

import java.time.LocalDate;

public record StudentInfoDto(String fullName,
                             Gender gender,
                             Integer dormitoryNumber,
                             LocalDate dateOfRoomCheckIn,
                             LocalDate dateOfRoomCheckOut,
                             Integer roomNumber,
                             LocalDate dateOfStartEducation,
                             LocalDate dateOfEndingEducation,
                             String universityName) {
}
