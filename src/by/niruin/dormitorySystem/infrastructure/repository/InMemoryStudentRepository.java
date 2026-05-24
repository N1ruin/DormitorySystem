package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.StudentMapper;
import by.niruin.dormitorySystem.util.FileUtil;

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

    @Override
    public void persistStudents() {
        String studentsData = mapper.mapStudentsToString(students.values());
        FileUtil.writeString(STUDENTS_FILE_PATH, studentsData);
    }

    @Override
    public void fetchStudents() {
        String studentsData = FileUtil.readString(STUDENTS_FILE_PATH);
        mapper.mapStringToStudents(studentsData)
                .forEach(student -> students.put(student.getId(), student));

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
    public List<Student> findByUniversityId(UUID universityId) {
        return students.values()
                .stream()
                .filter(student -> student.getUniversityId().equals(universityId))
                .toList();
    }

    @Override
    public List<Student> findAllByUniversityIdOrderBy(UUID universityId, Comparator<Student> comparator) {
        return students.values()
                .stream()
                .filter(student -> student.getUniversityId().equals(universityId))
                .sorted(comparator)
                .toList();
    }

    @Override
    public Map<UUID, List<Student>> findByDormitoryIdGroupingByRoomId(UUID dormitoryId, UUID roomId) {
        return students.values()
                .stream()
                .filter(student -> student.getDormitoryId().equals(dormitoryId))
                .filter(student -> student.getRoomId() != null)
                .collect(Collectors.groupingBy(Student::getRoomId));
    }

    @Override
    public List<Student> findByDormitoryId(UUID dormitoryId) {
        return students.values()
                .stream()
                .filter(student -> {
                    var studentDormitoryId = student.getDormitoryId();
                    return studentDormitoryId != null && studentDormitoryId.equals(dormitoryId);
                })
                .toList();
    }

    @Override
    public Optional<Student> findByUniversityIdAndFullName(UUID universityId, String fullName) {
        return students.values()
                .stream()
                .filter(student -> student.getUniversityId().equals(universityId))
                .filter(student -> student.getFullName().getFullNameString().equals(fullName))
                .findFirst();
    }

    @Override
    public List<Student> findByRoomId(UUID roomId) {
        return students.values()
                .stream()
                .filter(student -> student.getRoomId().equals(roomId))
                .toList();
    }
}
