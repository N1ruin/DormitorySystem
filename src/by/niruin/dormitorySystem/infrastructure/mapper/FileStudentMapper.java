package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.exception.EntityMappingException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.FIELDS_DELIMITER;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.NEW_LINE_SYMBOL;

@Component
public class FileStudentMapper implements StudentMapper {
    public static final int ADDITIONAL_FIELDS_FOR_FULLNAME = 2;
    public static final String NULL_STRING = "null";

    @Override
    public String mapStudentsToString(Collection<Student> students) {
        return students.stream()
                .map(this::studentFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    @Override
    public Collection<Student> mapStringToStudents(String studentsData) {
        if (studentsData == null || studentsData.trim().isEmpty()) {
            return List.of();
        }

        String[] roomsFields = studentsData.split(NEW_LINE_SYMBOL);

        return Arrays.stream(roomsFields)
                .map(this::mapStringToStudent)
                .toList();
    }

    private String studentFieldsToString(Student student) {
        String dateOfEntering;
        if (student.getDateOfStartEducation() == null) {
            dateOfEntering = NULL_STRING;
        } else {
            dateOfEntering = student.getDateOfStartEducation().toString();
        }
        String explusionDate;
        if (student.getDateOfEndingEducation() == null) {
            explusionDate = NULL_STRING;
        } else {
            explusionDate = student.getDateOfEndingEducation().toString();
        }
        String checkInDate;
        if (student.getDateOfRoomCheckIn() == null) {
            checkInDate = NULL_STRING;
        } else {
            checkInDate = student.getDateOfRoomCheckIn().toString();
        }
        String checkOutDate;
        if (student.getDateOfRoomCheckOut() == null) {
            checkOutDate = NULL_STRING;
        } else {
            checkOutDate = student.getDateOfRoomCheckOut().toString();
        }
        String universityId;
        if (student.getUniversityId() == null) {
            universityId = NULL_STRING;
        } else {
            universityId = student.getUniversityId().toString();
        }
        String roomId;
        if (student.getRoomId() == null) {
            roomId = NULL_STRING;
        } else {
            roomId = student.getRoomId().toString();
        }
        String dormitoryId;
        if (student.getDormitoryId() == null) {
            dormitoryId = NULL_STRING;
        } else {
            dormitoryId = student.getDormitoryId().toString();
        }
        return String.join(
                FIELDS_DELIMITER,
                student.getId().toString(),
                student.getFullName().getFirstName(),
                student.getFullName().getLastName(),
                student.getFullName().getFatherName(),
                student.getGender().toString(),
                universityId,
                roomId,
                dormitoryId,
                dateOfEntering,
                explusionDate,
                checkInDate,
                checkOutDate);
    }

    private Student mapStringToStudent(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);
        if (parts.length != Student.class.getDeclaredFields().length + FullName.class.getDeclaredFields().length - 1) {
            throw new EntityMappingException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String fatherName = parts[3];
        Gender gender = Gender.valueOf(parts[4]);

        UUID universityUuid;
        if (parts[5].equalsIgnoreCase(NULL_STRING)) {
            universityUuid = null;
        } else {
            universityUuid = UUID.fromString(parts[5]);
        }

        UUID roomId;
        if (parts[6].equalsIgnoreCase(NULL_STRING)) {
            roomId = null;
        } else {
            roomId = UUID.fromString(parts[6]);
        }

        UUID dormitoryId;
        if (parts[7].equalsIgnoreCase(NULL_STRING)) {
            dormitoryId = null;
        } else {
            dormitoryId = UUID.fromString(parts[7]);
        }

        LocalDate dateOfEntering;
        if (parts[8].equalsIgnoreCase(NULL_STRING)) {
            dateOfEntering = null;
        } else {
            dateOfEntering = LocalDate.parse(parts[8]);
        }

        LocalDate expulsionDate;
        if (parts[9].equalsIgnoreCase(NULL_STRING)) {
            expulsionDate = null;
        } else {
            expulsionDate = LocalDate.parse(parts[9]);
        }

        LocalDate roomCheckIn;
        if (parts[10].equalsIgnoreCase(NULL_STRING)) {
            roomCheckIn = null;
        } else {
            roomCheckIn = LocalDate.parse(parts[10]);
        }

        LocalDate roomCheckOut;
        if (parts[11].equalsIgnoreCase(NULL_STRING)) {
            roomCheckOut = null;
        } else {
            roomCheckOut = LocalDate.parse(parts[11]);
        }

        return new Student(id, new FullName(firstName, fatherName, lastName), gender, universityUuid, roomId,
                dormitoryId, dateOfEntering, expulsionDate, roomCheckIn, roomCheckOut);
    }
}
