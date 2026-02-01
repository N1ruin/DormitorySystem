package by.niruin.dormitorySystem.domain.model.dto;

public record UpdateRoomDto(int number,
                            byte capacity,
                            boolean availableForLiving,
                            boolean isMale) {
}
