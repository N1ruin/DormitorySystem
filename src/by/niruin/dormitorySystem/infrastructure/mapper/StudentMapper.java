package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.*;

import java.time.LocalDate;
import java.util.*;

public class StudentMapper extends AbstractEntityMapper<Student> {
    @Override
    protected int getFieldsCount() {
        return 10;
    }

    @Override
    protected String entityFieldsToString(Student student) {
        return String.join(
                FIELDS_DELIMITER,
                student.getUuid().toString(),
                student.getFullName().getFirstName(),
                student.getFullName().getLastName(),
                student.getFullName().getFatherName(),
                student.getGender().toString(),
                student.getUniversityUuid().toString(),
                student.getDormitoryUuid().toString(),
                student.getRoomUuid().toString(),
                String.valueOf(student.getYearOfEntering()),
                student.getDeductionDate().toString());
    }

    @Override
    protected Student mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != getFieldsCount()) {
            throw new RuntimeException("");
        }

        UUID uuid = UUID.fromString(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String fatherName = parts[3];
        Gender gender = Gender.valueOf(parts[4]);
        UUID universityUuid = UUID.fromString(parts[5]);
        UUID dormitoryUuid = UUID.fromString(parts[6]);
        UUID roomUuid = UUID.fromString(parts[7]);
        int yearOfEntering = Integer.parseInt(parts[8]);
        LocalDate deductionDate = LocalDate.parse(parts[9]);

        return new Student(uuid, firstName, lastName, fatherName, gender, deductionDate, yearOfEntering,
                roomUuid, dormitoryUuid, universityUuid);
    }
}
