package by.niruin.dormitorySystem.domain.model.dto;

public record CreateRoomDto(
        int number,
        byte capacity,
        boolean availableForLiving,
        boolean isMaleOnly) {
}