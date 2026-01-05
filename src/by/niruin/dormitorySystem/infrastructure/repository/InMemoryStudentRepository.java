package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.StudentMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InMemoryStudentRepository extends InMemoryRepositoryBase<Student> implements StudentRepository {
    public static final Path STUDENTS_FILE_PATH = Paths.get("./resources/entity/student.txt");

    public InMemoryStudentRepository(@Qualifier(StudentMapper.class) EntityMapper<Student> studentMapper) {
        super(studentMapper, STUDENTS_FILE_PATH);
    }

    @Override
    public List<Student> findByRoomId(UUID roomId) {
        return entities.values().stream()
                .filter(student -> student.getRoomId().equals(roomId)).toList();
    }

    @Override
    public Map<UUID, List<Student>> getStudentsInRooms() {
        UUID currentDormitoryId = ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
        return entities.values().stream()
                .filter(student -> student.getDormitoryId().equals(currentDormitoryId))
                .filter(student -> student.getDormitoryId() != null &&
                                   student.getDormitoryId().equals(currentDormitoryId))
                .filter(student -> student.getRoomId() != null)
                .collect(Collectors.groupingBy(Student::getRoomId));
    }

    @Override
    public List<String> getStudentNamesWithoutRoom(UUID dormitoryId) {
        return entities.values().stream()
                .filter(student -> student.getDormitoryId().equals(dormitoryId))
                .filter(student -> student.getRoomId() == null)
                .map(student -> String.join(
                        " ",
                        student.getFullName().getLastName(),
                        student.getFullName().getFirstName(),
                        student.getFullName().getFatherName()))
                .toList();
    }
}
