package by.niruin.dormitorySystem.domain.model.dto.university;

import java.util.List;
import java.util.Objects;

public record UniversityInfoDto(String universityName,
                                Byte studyDuration,
                                List<Integer> universityDormitoriesNumbers) {

    public UniversityInfoDto {
        Objects.requireNonNull(universityDormitoriesNumbers);
    }
}
