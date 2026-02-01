package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.model.FullName;
import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.EntityValidationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;

public class StudentValidationService {
    private final StudentRepository studentRepository;
    private final RoomService roomService;

    public StudentValidationService(StudentRepository studentRepository, RoomService roomService) {
        this.studentRepository = studentRepository;
        this.roomService = roomService;
    }

    public void validateStudentExist(FullName fullName, LocalDate dateOfEntering) {
        boolean isStudentExist = studentRepository.findAll().stream()
                .filter(student -> student.getFullName().equals(fullName))
                .anyMatch(student -> student.getDateOfStartEducation().equals(dateOfEntering));

        if (isStudentExist) {
            throw new EntityValidationException(CREATING_STUDENT_FAIL_MESSAGE);
        }
    }

    public void validateStudentsExists(List<Student> students) {
        if (students.isEmpty()) {
            throw new EntityNotFoundException(STUDENTS_NOT_FOUND_MESSAGE);
        }
    }

    public void validateGetStudentNamesFromList(List<String> studentsNames, int numberFromList) {
        List<String> validationErrors = new ArrayList<>();

        if (studentsNames.isEmpty()) {
            throw new EntityNotFoundException(STUDENTS_NOT_FOUND_MESSAGE);
        }

        if (numberFromList <= 0 || numberFromList > studentsNames.size()) {
            throw new IndexOutOfBoundsException(INVALID_INPUT_MESSAGE);
        }

        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(GET_STUDENT_FROM_LIST_FAIL_MESSAGE + errors);
        }
    }

    public void validateRoomForStudent(Student student, Room room) {
        if ((student.getGender() == Gender.MALE && !room.isMaleOnly()) ||
            (student.getGender() == Gender.FEMALE && room.isMaleOnly())) {
            throw new EntityValidationException(INVALID_STUDENT_GENDER_FROM_ROOM_MESSAGE.formatted(room.isMaleOnly() ? "male" : "female"));
        }

        if (!room.isAvailableForLiving()) {
            throw new EntityValidationException(ROOM_IS_NOT_AVAILABLE_FOR_LIVING_MESSAGE);
        }

        int freePlaces = roomService.getFreePlaces(room.getId());
        if (freePlaces <= 0) {
            throw new EntityValidationException(ROOM_IS_FULL_MESSAGE);
        }

        if (!room.getDormitoryId().equals(student.getDormitoryId())) {
            throw new EntityValidationException(ROOM_IS_IN_DIFFERENT_DORMITORY_MESSAGE);
        }
    }
}
