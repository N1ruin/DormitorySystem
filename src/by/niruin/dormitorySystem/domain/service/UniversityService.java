package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.*;
import by.niruin.dormitorySystem.domain.model.dto.university.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.validation.UniversityValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.UniversityFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.Comparator;
import java.util.UUID;

@Component
public class UniversityService {
    public static final String UNIVERSITY_NAMES_TITLE = "University formattedNamesData:\n";
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UniversityValidationService validationService;
    private final UniversityFormatter universityFormatter;

    public UniversityService(UniversityRepository universityRepository, DormitoryRepository dormitoryRepository,
                             UniversityValidationService validationService, UniversityFormatter universityFormatter) {
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.validationService = validationService;
        this.universityFormatter = universityFormatter;
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
        var universityName = getUniversityNameByListNumber(dto.selectedUniversityNumber());
        var university = universityRepository.findByName(universityName)
                .orElseThrow(() -> new EntityNotFoundException(universityName, University.class));
        universityRepository.delete(university.getId());
    }

    public void updateUniversity(UpdateUniversityDto dto) {
        var universityName = getUniversityNameByListNumber(dto.numberFromList());
        var university = universityRepository.findByName(universityName)
                .orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

        university.setStudyDuration(dto.studyDuration());
    }

    public String getUniversityInfo(UniversityNumberDto dto) {
        var universityName = getUniversityNameByListNumber(dto.selectedUniversityNumber());
        var university = universityRepository.findByName(universityName)
                .orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

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

        var university = universityRepository.findByName(universityName)
                .orElseThrow(() -> new EntityNotFoundException(universityName, University.class));

        ApplicationContextUtil.getActiveUser().setUniversityId(university.getId());
    }

    public boolean isCurrentUniversityHasDormitories() {
        return !dormitoryRepository.findAllByUniversityId(ApplicationContextUtil.getCurrentUniversityId()).isEmpty();
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
