package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.dto.dormitory.*;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.formatter.DormitoryFormatter;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class DormitoryService {
    public static final String DORMITORIES_NUMBERS_TITLE = "Dormitories numbers:\n";
    private final DormitoryRepository dormitoryRepository;
    private final DormitoryValidationService dormitoryValidationService;
    private final DormitoryFormatter dormitoryFormatter;

    public DormitoryService(DormitoryRepository dormitoryRepository, DormitoryValidationService validationService, DormitoryFormatter dormitoryFormatter) {
        this.dormitoryRepository = dormitoryRepository;
        this.dormitoryValidationService = validationService;
        this.dormitoryFormatter = dormitoryFormatter;
    }

    public String getDormitoryNumbers(UUID universityId) {
        var dormitories = dormitoryRepository.findByUniversityId(universityId);

        dormitoryValidationService.validateDormitoriesExist(dormitories);

        var dormitoriesNumbers = dormitories.stream()
                .map(Dormitory::getNumber)
                .sorted()
                .toList();

        return dormitoryFormatter.formatDormitoriesNumbersToNumeredNumbers(dormitoriesNumbers, DORMITORIES_NUMBERS_TITLE);
    }

    public String getCurrentUniversityDormitoryNumbers() {
        return getDormitoryNumbers(ApplicationContextUtil.getCurrentUniversityId());
    }

    public void create(CreateDormitoryDto dto) {
        dormitoryValidationService.validateCreateData(dto);

        UUID id = UUID.randomUUID();
        UUID currentUniversityId = ApplicationContextUtil.getCurrentUniversityId();
        Dormitory dormitory = new Dormitory(id, dto.number(), dto.roomsCount(), currentUniversityId, dto.availableForLiving());

        dormitoryRepository.save(dormitory);
    }

    public void delete(DeleteDormitoryDto dto) {
        dormitoryValidationService.validateDormitoryNumber(dto.number());

        var dormitory = dormitoryRepository.findByUniversityIdAndNumber(ApplicationContextUtil.getCurrentUniversityId(), dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Dormitory.class));

        dormitoryRepository.delete(dormitory.getId());
    }

    public void update(UpdateDormitoryDto dto) {
        dormitoryValidationService.validateDormitoryNumber(dto.number());

        var dormitory = dormitoryRepository.findByUniversityIdAndNumber(ApplicationContextUtil.getCurrentUniversityId(), dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Dormitory.class));

        dormitory.setAvailableForLiving(dto.availableForLiving());

        dormitoryRepository.update(dormitory);
    }

    public String getDormitoryInfo(SelectedDormitoryNumberFromList dto) {
        var dormitory = dormitoryRepository.findByUniversityIdAndNumber(ApplicationContextUtil.getCurrentUniversityId(), dto.number())
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Dormitory.class));

        var dormitoryInfoDto = buildDormitoryInfoDto(dormitory);

        return dormitoryFormatter.formatDormitoriesToDormitoriesInfo(dormitoryInfoDto);
    }

    public String getSortedDormitoriesInfo(Comparator<Dormitory> comparator) {
        var dormitories = dormitoryRepository.findAllByUniversityIdOrderBy(ApplicationContextUtil.getCurrentUniversityId(), comparator);

        var dormitoryInfoDtos = dormitories.stream()
                .map(this::buildDormitoryInfoDto)
                .toList();

        return dormitoryFormatter.formatDormitoriesToDormitoriesInfo(dormitoryInfoDtos.toArray(DormitoryInfoDto[]::new));
    }

    public void updateCurrentDormitory(SelectCurrentDormitoryDto dto) {
        if (dto == null) {
            ApplicationContextUtil.getActiveUser().setDormitoryId(null);
            return;
        }

        int dormitoryNumber = dto.number();

        var dormitory = dormitoryRepository.findByUniversityIdAndNumber(ApplicationContextUtil.getCurrentUniversityId(), dormitoryNumber)
                .orElseThrow(() -> new EntityNotFoundException(dto.number(), Dormitory.class));

        ApplicationContextUtil.getActiveUser().setDormitoryId(dormitory.getId());
    }

    public UUID getDormitoryIdFromCurrentUniversityByListNumber(int numberFromList) {
        return dormitoryRepository.findAllByUniversityId(ApplicationContextUtil.getCurrentUniversityId())
                .stream()
                .sorted(Comparator.comparingInt(Dormitory::getNumber))
                .toList()
                .get(numberFromList - 1)
                .getId();
    }

    public List<Dormitory> getAllFromCurrentUniversity() {
        return dormitoryRepository.findAllByUniversityId(ApplicationContextUtil.getCurrentUniversityId());
    }

    private DormitoryInfoDto buildDormitoryInfoDto(Dormitory dormitory) {
        var number = dormitory.getNumber();
        var dormitoryRoomsCount = dormitory.getRoomsCount();
        var availableForLiving = dormitory.isAvailableForLiving();

        return new DormitoryInfoDto(number, dormitoryRoomsCount, availableForLiving);
    }
}
