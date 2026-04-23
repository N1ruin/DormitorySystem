package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.CreateRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.DeleteRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.RoomInfoFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.UpdateRoomFormHandler;

@Component
public class RoomFormHandlerFactory {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final RoomInputValidationService roomInputValidationService;
    private final RoomService roomService;

    public RoomFormHandlerFactory(FormHandler formHandler, PrintService printService,
                                  RoomInputValidationService roomInputValidationService, RoomService roomService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.roomInputValidationService = roomInputValidationService;
        this.roomService = roomService;
    }

    public CreateRoomFormHandler getCreateRoomFormHandler() {
        return new CreateRoomFormHandler(formHandler, roomInputValidationService, printService);
    }

    public DeleteRoomFormHandler getDeleteRoomFormHandler() {
        return new DeleteRoomFormHandler(printService, roomService, formHandler, roomInputValidationService);
    }

    public RoomInfoFormHandler getRoomInfoFormHandler() {
        return new RoomInfoFormHandler(formHandler, printService, roomService, roomInputValidationService);
    }

    public UpdateRoomFormHandler getUpdateRoomFormHandler() {
        return new UpdateRoomFormHandler(formHandler, roomInputValidationService, printService, roomService);
    }
}
