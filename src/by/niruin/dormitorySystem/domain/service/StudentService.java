package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.model.dto.student.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryValidationService;
import by.niruin.dormitorySystem.domain.service.validation.StudentValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.EntityValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.StudentFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class StudentService {
    public static final String LIST_OF_STUDENTS_TITLE = "List of students:\n";
    public static final String LIST_OF_STUDENTS_WITHOUT_ROOM_TITLE = "List of students without room:\n";
    public static final String LIST_OF_STUDENTS_WITHOUT_DORMITORY_TITLE = "List of students without dormitory:\n";

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final StudentValidationService studentValidationService;
    private final DormitoryValidationService dormitoryValidationService;
    private final DormitoryService dormitoryService;
    private final StudentFormatter studentFormatter;
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    public StudentService(StudentRepository studentRepository, UniversityRepository universityRepository,
                          StudentValidationService studentValidationService, DormitoryValidationService dormitoryValidationService, DormitoryService dormitoryService,
                          StudentFormatter studentFormatter, DormitoryRepository dormitoryRepository, RoomRepository roomRepository, RoomService roomService) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.studentValidationService = studentValidationService;
        this.dormitoryValidationService = dormitoryValidationService;
        this.dormitoryService = dormitoryService;
        this.studentFormatter = studentFormatter;
        this.dormitoryRepository = dormitoryRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    public void create(CreateStudentDto dto) {
        var fullName = new FullName(dto.firstName(), dto.fatherName(), dto.lastName());
        studentValidationService.validateStudentExist(fullName, dto.enteringDate());

        var uuid = UUID.randomUUID();
        var currentUniversity = universityRepository.findById(ApplicationContextUtil.getCurrentUniversityId())
                .orElseThrow(() -> new EntityNotFoundException(fullName.getFullNameString(), Student.class));
        LocalDate startEducationDate = dto.enteringDate();
        var endingEducationDate = startEducationDate.plusYears(currentUniversity.getStudyDuration());

        var student = new Student(uuid, fullName, dto.gender(), ApplicationContextUtil.getCurrentUniversityId(),
                null, null, startEducationDate, endingEducationDate, null, null);

        studentRepository.save(student);
    }

    public void delete(DeleteStudentDto dto) {
        var studentName = getStudentNameByListNumber(dto.numberFromList());
        var student = getByName(studentName);
        studentRepository.delete(student.getId());
    }

    public void update(UpdateStudentDto dto) {
        var studentName = getStudentNameByListNumber(dto.numberFromList());
        var student = getByName(studentName);

        var dormitoryId = dormitoryService.getDormitoryIdFromCurrentUniversityByListNumber(dto.dormitoryNumberFromList());

        student.setDormitoryId(dormitoryId);
        student.getFullName().setLastName(dto.lastName());

        if (!student.getDormitoryId().equals(dormitoryId)) {
            student.setRoomId(null);
        }
    }

    public String getStudentInfo(StudentNumberFromListDto dto) {
        var studentName = getStudentNameByListNumber(dto.numberFromList());
        var student = getByName(studentName);

        var infoDto = buildInfoDto(student);

        return studentFormatter.formatStudentsToStudentsInfo(infoDto);
    }

    public String getStudentsSortedInfo(Comparator<Student> comparator) {
        var sortedStudentList = studentRepository
                .findAllByUniversityIdOrderBy(ApplicationContextUtil.getCurrentUniversityId(), comparator);

        var infoDtoList = sortedStudentList.stream()
                .map(this::buildInfoDto)
                .toList();

        return studentFormatter.formatStudentsToStudentsInfo(infoDtoList.toArray(StudentInfoDto[]::new));
    }

    public String getCurrentUniversityStudentNames() {
        var currentUniversityId = ApplicationContextUtil.getCurrentUniversityId();
        Predicate<Student> isStudentFromCurrentUniversity =
                student -> student.getUniversityId().equals(currentUniversityId);

        return getFilteredStudentNames(isStudentFromCurrentUniversity, LIST_OF_STUDENTS_TITLE);
    }

    public String getStudentsNamesWithoutDormitory() {
        return getFilteredStudentNames(
                student -> student.getDormitoryId() == null,
                LIST_OF_STUDENTS_WITHOUT_DORMITORY_TITLE);
    }

    public String getStudentNamesWithoutRoom() {
        var students = studentRepository.findByDormitoryId(ApplicationContextUtil.getCurrentDormitoryId())
                .stream()
                .filter(student -> student.getRoomId() == null)
                .toList();

        studentValidationService.validateStudentsExists(students);

        var studentNames = students.stream()
                .map(this::mapStudentToNames)
                .sorted()
                .toList();

        return studentFormatter.formatStudentNamesToNumeredNames(studentNames, LIST_OF_STUDENTS_WITHOUT_ROOM_TITLE);
    }

    public void distributeStudentToDormitory(DistributeStudentToDormitoryDto dto) {
        var studentName = getWithoutDormitoryStudentNameByListNumber(dto.studentNumberFromList());

        var student = getByName(studentName);

        var dormitory = dormitoryRepository.findByDormitoryNumberOrUniversityId(ApplicationContextUtil.getCurrentUniversityId(),
                dto.dormitoryNumber()).orElseThrow(() -> new EntityNotFoundException(dto.dormitoryNumber(), Dormitory.class));

        dormitoryValidationService.validateDormitoryHaveFreeRooms(student.getGender(), dormitory.getId());

        student.setRoomId(null);
        student.setDormitoryId(dormitory.getId());
    }

    public List<Student> getStudentsWithoutDormitory() {
        return studentRepository.findByUniversityId(ApplicationContextUtil.getCurrentUniversityId())
                .stream()
                .filter(student -> student.getDormitoryId() == null)
                .toList();
    }

    public void distributeStudentToRoom(DistributeStudentToRoomDto dto) {
        var studentName = getNameWithoutRoomByListNumber(dto.studentNumberFromList());
        var student = getByName(studentName);

        if (student.getDormitoryId() == null) {
            throw new EntityValidationException("Student must be assigned to a dormitory first");
        }

        UUID currentDormitoryId = ApplicationContextUtil.getCurrentDormitoryId();

        List<Room> freeRooms = roomService.getFreeRooms(student.getGender(), currentDormitoryId);

        Room selectedRoom = freeRooms.get(dto.roomNumber() - 1);

        studentValidationService.validateRoomForStudent(student, selectedRoom);

        student.setRoomId(selectedRoom.getId());

        studentRepository.update(student);
    }

    public Student getByName(String studentName) {
        return studentRepository.findByUniversityIdAndFullName(ApplicationContextUtil.getCurrentUniversityId(), studentName)
                .orElseThrow(() -> new EntityNotFoundException(studentName, Student.class));
    }

    private String getNameWithoutRoomByListNumber(int numberFromList) {
        return getStudentNameByListNumber(numberFromList, student -> student.getRoomId() == null);
    }

    private String getStudentNameByListNumber(int numberFromList, Predicate<Student> filter) {
        var studentsNames = getFilteredStudentNames(filter);
        studentValidationService.validateGetStudentNamesFromList(studentsNames, numberFromList);
        return studentsNames.getLast();
    }

    private String getWithoutDormitoryStudentNameByListNumber(int numberFromList) {
        return getStudentNameByListNumber(numberFromList, student -> student.getDormitoryId() == null);
    }

    private String getStudentNameByListNumber(int numberFromList) {
        return getStudentNameByListNumber(numberFromList, _ -> true);
    }

    private List<String> getFilteredStudentNames(Predicate<Student> filter) {
        return studentRepository.findByUniversityId(ApplicationContextUtil.getCurrentUniversityId())
                .stream()
                .filter(filter)
                .map(this::mapStudentToNames)
                .sorted()
                .toList();
    }

    private String getFilteredStudentNames(Predicate<Student> filter, String title) {
        var students = studentRepository.findByUniversityId(ApplicationContextUtil.getCurrentUniversityId())
                .stream()
                .filter(filter)
                .toList();

        studentValidationService.validateStudentsExists(students);

        var studentNames = students.stream()
                .map(this::mapStudentToNames)
                .sorted()
                .toList();

        return studentFormatter.formatStudentNamesToNumeredNames(studentNames, title);
    }

    private StudentInfoDto buildInfoDto(Student student) {
        var fullName = student.getFullName().getFullNameString();
        var gender = student.getGender();

        String universityName = universityRepository.findById(student.getUniversityId())
                .map(University::getName)
                .orElse(null);

        LocalDate startEducationDate = student.getStartEducationDate();
        LocalDate endingEducationDate = student.getEndingEducationDate();
        Integer dormitoryNumber = getDormitoryNumber(student);
        Integer roomNumber = getRoomNumber(student);
        LocalDate roomCheckInDate = student.getRoomCheckInDate();
        LocalDate roomCheckOutDate = student.getRoomCheckOutDate();

        return new StudentInfoDto(fullName,
                gender,
                dormitoryNumber,
                roomCheckInDate,
                roomCheckOutDate,
                roomNumber,
                startEducationDate,
                endingEducationDate,
                universityName);
    }

    private Integer getDormitoryNumber(Student student) {
        return Optional.ofNullable(student.getDormitoryId())
                .flatMap(dormitoryRepository::findById)
                .map(Dormitory::getNumber)
                .orElse(null);
    }

    public List<Student> getStudentsWithoutRoom() {
        UUID currentDormitoryId = ApplicationContextUtil.getCurrentDormitoryId();
        return studentRepository.findByDormitoryId(currentDormitoryId)
                .stream()
                .filter(student -> student.getRoomId() == null)
                .toList();
    }

    private Integer getRoomNumber(Student student) {
        if (student.getRoomId() != null) {
            return roomRepository.findById(student.getRoomId())
                    .map(Room::getNumber)
                    .orElse(null);
        }
        return null;
    }

    private String mapStudentToNames(Student student) {
        return student.getFullName().getFullNameString();
    }
}
