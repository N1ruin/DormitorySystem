package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.dto.StudentsWithoutRoomDto;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryStudentRepository;

import java.util.List;

@Component
public class StudentService {
    public static final String LIST_OF_NOT_LIVING_STUDENTS_MESSAGE = "List of not living students:";
    private final StudentRepository studentRepository;

    public StudentService(@Qualifier(InMemoryStudentRepository.class) StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentsWithoutRoomDto getStudentsNamesWithoutRoom() {
        var dormitoryId = ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
        List<String> studentNames = studentRepository.getStudentNamesWithoutRoom(dormitoryId);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(LIST_OF_NOT_LIVING_STUDENTS_MESSAGE);
        for (int i = 1; i <= studentNames.size(); i++) {
            stringBuilder.append("%d. %s\n".formatted(i, studentNames.get(i - 1)));
        }

        return new StudentsWithoutRoomDto(stringBuilder.toString());
    }
}
