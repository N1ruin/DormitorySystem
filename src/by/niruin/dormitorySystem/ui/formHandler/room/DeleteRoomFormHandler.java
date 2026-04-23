package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.room.DeleteRoomDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class DeleteRoomFormHandler {
    private final PrintService printService;
    private final RoomService roomService;
    private final FormHandler formHandler;
    private final RoomInputValidationService roomInputValidationService;
    private int roomNumber;

    public DeleteRoomFormHandler(PrintService printService, RoomService roomService, FormHandler formHandler,
                                 RoomInputValidationService roomInputValidationService) {
        this.printService = printService;
        this.roomService = roomService;
        this.formHandler = formHandler;
        this.roomInputValidationService = roomInputValidationService;
    }

    public DeleteRoomFormHandler inputRoomNumber() {
        roomNumber = formHandler.handleInputString(
                () -> printService.printRoomNumbers(roomService.getRoomNumbers()),
                Integer::parseInt,
                roomInputValidationService::validateNumber);
        return this;
    }

    public DeleteRoomDto createDto() {
        return new DeleteRoomDto(roomNumber);
    }
}
