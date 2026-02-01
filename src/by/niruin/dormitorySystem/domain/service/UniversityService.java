package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.model.dto.university.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.validation.UniversityValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.UniversityFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UniversityService {
    public static final String UNIVERSITY_NAMES_TITLE = "University names:\n";
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UniversityValidationService validationService;
    private final UniversityFormatter universityFormatter;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public UniversityService(UniversityRepository universityRepository, DormitoryRepository dormitoryRepository, UniversityValidationService validationService, UniversityFormatter universityFormatter, StudentRepository studentRepository, RoomRepository roomRepository) {
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.validationService = validationService;
        this.universityFormatter = universityFormatter;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    public UniversityNamesDto getUniversitiesNames() {
        var universityNames = universityRepository.findAll().stream()
                .map(University::getName)
                .sorted()
                .toList();

        var universityNumberedNamesList = universityFormatter.formatStudentNamesToNumeredNames(universityNames, UNIVERSITY_NAMES_TITLE);

        return new UniversityNamesDto(universityNumberedNamesList);
    }

    public void createUniversity(CreateUniversityDto dto) {
        validationService.validateCreateData(dto);

        UUID universityId = UUID.randomUUID();
        University university = new University(universityId, dto.universityName(), dto.studyDuration());

        universityRepository.save(university);
    }

    public void deleteUniversity(DeleteUniversityDto dto) {
        var universityName = getUniversityNameByListNumber(dto.universityNumberFromList());
        var university = universityRepository.findByName(universityName).orElseThrow(() -> new EntityNotFoundException(universityName, University.class));
        universityRepository.delete(university.getId());
    }

    public void updateUniversity(UpdateUniversityDto dto) {
        var universityName = getUniversityNameByListNumber(dto.numberFromList());
        var university = universityRepository.findByName(universityName).orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

        university.setStudyDuration(dto.studyDuration());
    }

    public String getUniversityInfo(UniversityNumberDto dto) {
        var universityName = getUniversityNameByListNumber(dto.numberFromList());
        var university = universityRepository.findByName(universityName).orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

        var universityInfoDto = buildInfoDto(university);
        return universityFormatter.formatUniversitiesToUniversitiesInfo(universityInfoDto);
    }

    public String getSortedUniversitiesInfo(Comparator<University> comparator) {
        var sortedUniversitiesList = universityRepository.findAllOrderBy(comparator);

        var infoDtoList = sortedUniversitiesList.stream()
                .map(this::buildInfoDto)
                .toList();

        return universityFormatter.formatUniversitiesToUniversitiesInfo(infoDtoList.toArray(UniversityInfoDto[]::new));
    }

    public void updateCurrentUniversity(SelectedCurrentUniversityNumberFromListDto dto) {
        var universityNumber = dto.selectedUniversityNumber();
        var universityName = getUniversityNameByListNumber(universityNumber);

        var university = universityRepository.findByName(universityName).orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

        ApplicationContextUtil.getActiveUser().setUniversityId(university.getId());
    }

    public boolean isCurrentUniversityHasDormitories() {
        return !dormitoryRepository.findAllByUniversityId(ApplicationContextUtil.getCurrentUniversityId()).isEmpty();
    }

    public String getCurrentUniversityStatistics() {
        var universityId = ApplicationContextUtil.getCurrentUniversityId();
        var universityName = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException(universityId, University.class))
                .getName();

        var dormitories = dormitoryRepository.findByUniversityId(universityId);

        var dormitoriesCount = dormitories.size();
        var availableDormitoriesCount = dormitories.stream()
                .filter(Dormitory::isAvailableForLiving)
                .count();
        var notAvailableDormitoriesCount = dormitoriesCount - availableDormitoriesCount;

        int roomsCountFromAllDormitories = dormitories.stream()
                .mapToInt(Dormitory::getRoomsCount)
                .sum();

        var availableRooms = dormitories.stream()
                .map(dormitory -> roomRepository.findByDormitoryId(dormitory.getId()))
                .flatMap(List::stream)
                .filter(Room::isAvailableForLiving)
                .toList();

        var availableMaleRoomsCount = availableRooms.stream()
                .filter(Room::isMaleOnly)
                .count();

        var availableFemaleRoomsCount = roomsCountFromAllDormitories - availableMaleRoomsCount;

        var students = studentRepository.findByUniversityId(universityId);

        var studentsCount = students.size();
        var studentsByGender = students.stream()
                .collect(Collectors.groupingBy(Student::getGender));

        int maleStudents = studentsByGender.getOrDefault(Gender.MALE, List.of()).size();
        int femaleStudents = studentsByGender.getOrDefault(Gender.FEMALE, List.of()).size();

        var studentsByDormitoryStatus = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getDormitoryId() != null));

        int studentsInDormitories = studentsByDormitoryStatus.getOrDefault(true, List.of()).size();
        int studentsWithoutDormitory = studentsByDormitoryStatus.getOrDefault(false, List.of()).size();

        var dto = new UniversityStatisticsDto(universityName, dormitoriesCount, availableDormitoriesCount, notAvailableDormitoriesCount, roomsCountFromAllDormitories,
                availableMaleRoomsCount, availableFemaleRoomsCount, studentsCount, maleStudents, femaleStudents,
                studentsInDormitories, studentsWithoutDormitory);

        return universityFormatter.formatStatistics(dto);
    }

    private UniversityInfoDto buildInfoDto(University university) {
        var universityName = university.getName();
        var studyDuration = university.getStudyDuration();
        var dormitoriesFromUniversity = dormitoryRepository.findByUniversityId(university.getId()).stream()
                .map(Dormitory::getNumber)
                .sorted()
                .toList();

        return new UniversityInfoDto(universityName, studyDuration, dormitoriesFromUniversity);
    }

    private String getUniversityNameByListNumber(int inputNumberInList) {
        var universityNames = universityRepository.findAll().stream()
                .map(University::getName)
                .sorted()
                .toList();

        validationService.validateUniversitiesExist(universityNames);
        validationService.validateNumberInList(inputNumberInList, universityNames);

        return universityNames.get(inputNumberInList - 1);
    }
}
