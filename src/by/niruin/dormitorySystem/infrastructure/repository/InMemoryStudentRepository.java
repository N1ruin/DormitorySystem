package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.StudentMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryStudentRepository implements StudentRepository {
    private final Map<UUID, Student> students = new HashMap<>();
    private final StudentMapper mapper;
    public static final Path STUDENTS_FILE_PATH = Paths.get("./resources/entity/student.txt");

    public InMemoryStudentRepository(StudentMapper mapper) {
        this.mapper = mapper;
    }

    public void persistStudents() {
        String studentsData = mapper.mapStudentsToString(students.values());
        FileUtil.writeString(STUDENTS_FILE_PATH, studentsData);
    }

    public void fetchStudents() {
        try {
            String studentsData = FileUtil.readString(STUDENTS_FILE_PATH);
            mapper.mapStringToStudents(studentsData)
                    .forEach(student -> students.put(student.getId(), student));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void save(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public List<Student> findAll() {
        return List.copyOf(students.values());
    }

    @Override
    public void update(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public void delete(UUID id) {
        students.remove(id);
    }

    @Override
    public Map<UUID, List<Student>> getStudentsInRooms() {
        UUID currentDormitoryId = ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
        return students.values().stream()
                .filter(student -> student.getDormitoryId().equals(currentDormitoryId))
                .filter(student -> student.getDormitoryId() != null &&
                                   student.getDormitoryId().equals(currentDormitoryId))
                .filter(student -> student.getRoomId() != null)
                .collect(Collectors.groupingBy(Student::getRoomId));
    }

    @Override
    public List<String> getStudentNamesWithoutRoom(UUID dormitoryId) {
        return students.values().stream()
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
