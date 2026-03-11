package by.niruin.dormitorySystem.domain.model.dto.university;

import java.util.ArrayList;
import java.util.List;

public record UniversityInfoDto(String universityName,
                                Byte studyDuration,
                                List<Integer> dormitoriesNumbersFromUniversity) {

    public UniversityInfoDto {
        if (dormitoriesNumbersFromUniversity == null) {
            dormitoriesNumbersFromUniversity = new ArrayList<>();
        }
    }
}
