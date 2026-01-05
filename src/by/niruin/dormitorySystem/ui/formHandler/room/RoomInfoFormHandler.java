package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.GetRoomInfoDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

@Component
public class RoomInfoFormHandler {
    private final FormHandler formHandleService;
    private final PrintService printService;
    private final RoomService roomService;
    private final RoomInputValidationService validationService;

    private int roomNumber;

    public RoomInfoFormHandler(FormHandler formHandleService, PrintService printService, RoomService roomService,
                               RoomInputValidationService roomInputValidationService) {
        this.formHandleService = formHandleService;
        this.printService = printService;
        this.roomService = roomService;
        this.validationService = roomInputValidationService;
    }

    public RoomInfoFormHandler handleRoomNumber() {
        roomNumber = formHandleService.handleInputString(
                () -> printService.printRoomNumbers(roomService.getRoomNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public GetRoomInfoDto createDto() {
        return new GetRoomInfoDto(roomNumber);
    }
}
