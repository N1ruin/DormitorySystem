package by.niruin.dormitorySystem.domain.service.statistic;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.model.dto.university.UniversityStatisticsDto;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.domain.repository.StudentRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.UniversityFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UniversityStatisticService {
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final UniversityFormatter universityFormatter;

    public UniversityStatisticService(UniversityRepository universityRepository, DormitoryRepository dormitoryRepository,
                                      RoomRepository roomRepository, StudentRepository studentRepository,
                                      UniversityFormatter universityFormatter) {
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.universityFormatter = universityFormatter;
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

        var dto = new UniversityStatisticsDto(universityName,
                dormitoriesCount,
                availableDormitoriesCount,
                notAvailableDormitoriesCount,
                roomsCountFromAllDormitories, availableMaleRoomsCount,
                availableFemaleRoomsCount,
                studentsCount,
                maleStudents,
                femaleStudents,
                studentsInDormitories,
                studentsWithoutDormitory);

        return universityFormatter.formatStatistics(dto);
    }
}
