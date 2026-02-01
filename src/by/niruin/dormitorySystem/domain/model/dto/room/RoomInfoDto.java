package by.niruin.dormitorySystem.domain.model.dto.room;

import by.niruin.dormitorySystem.domain.model.Gender;

import java.util.List;

public record RoomInfoDto(Integer number,
                          Gender gender,
                          Integer roomCapacity,
                          Boolean isFull,
                          Integer inhabitantsCount,
                          Boolean availableForLiving,
                          List<String> studentsNames) {
}
