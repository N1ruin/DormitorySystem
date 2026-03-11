package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.model.dto.university.CreateUniversityDto;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.EntityValidationException;

import java.util.ArrayList;
import java.util.List;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;

public class UniversityValidationService {
    private final UniversityRepository universityRepository;

    public UniversityValidationService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public void validateCreateData(CreateUniversityDto dto) {
        List<String> validationErrors = new ArrayList<>();

        if (isNameExist(dto.universityName())) {
            validationErrors.add(UNIVERSITY_NAME_EXIST_MESSAGE);
        }

        if (isStudyDurationLessThanZero(dto.studyDuration())) {
            validationErrors.add(DORMITORY_NUMBER_CANNOT_BE_LESS_THAN_ZERO_MESSAGE);
        }

        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(CREATING_UNIVERSITY_FAIL_MESSAGE + errors);
        }
    }

    public void validateUniversitiesExist(List<String> universityNames) {
        if (universityNames.isEmpty()) {
            throw new EntityNotFoundException(UNIVERSITIES_NOT_FOUND_MESSAGE);
        }
    }

    public void validateNumberInList(int inputNumberInList, List<String> universityNames) {
        if (inputNumberInList < 1 || inputNumberInList > universityNames.size()) {
            throw new EntityNotFoundException(UNIVERSITY_WITH_NUMBER_NOT_FOUND_MESSAGE.formatted(inputNumberInList, universityNames.size()));
        }
    }

    private boolean isNameExist(String name) {
        return universityRepository.findByName(name).isPresent();
    }

    private boolean isStudyDurationLessThanZero(byte studyDuration) {
        return studyDuration <= 0;
    }
}
