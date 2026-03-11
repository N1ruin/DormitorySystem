package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.room.RoomNumberFromListDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class RoomInfoFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final RoomService roomService;
    private final RoomInputValidationService validationService;

    private int roomNumber;

    public RoomInfoFormHandler(FormHandler formHandler, PrintService printService, RoomService roomService,
                               RoomInputValidationService roomInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.roomService = roomService;
        this.validationService = roomInputValidationService;
    }

    public RoomInfoFormHandler handleRoomNumber() {
        roomNumber = formHandler.handleInputString(
                () -> printService.printRoomNumbers(roomService.getRoomNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public RoomNumberFromListDto createDto() {
        return new RoomNumberFromListDto(roomNumber);
    }
}
