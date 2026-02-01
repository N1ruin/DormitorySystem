package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.room.DeleteRoomDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

@Component
public class DeleteRoomFormHandler {
    private final PrintService printService;
    private final RoomService roomService;
    private final FormHandler formHandleService;
    private final RoomInputValidationService validationService;
    private int roomNumber;

    public DeleteRoomFormHandler(PrintService printService, RoomService roomService, FormHandler formHandleService,
                                 RoomInputValidationService validationService) {
        this.printService = printService;
        this.roomService = roomService;
        this.formHandleService = formHandleService;
        this.validationService = validationService;
    }

    public DeleteRoomFormHandler handleRoomNumber() {
        roomNumber = formHandleService.handleInputString(
                () -> printService.printRoomNumbers(roomService.getRoomNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public DeleteRoomDto createDto() {
        return new DeleteRoomDto(roomNumber);
    }
}
