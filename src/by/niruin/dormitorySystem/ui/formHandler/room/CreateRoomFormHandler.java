package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.room.CreateRoomDto;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class CreateRoomFormHandler {
    private final FormHandler formHandler;
    private final RoomInputValidationService roomInputValidationService;
    private final PrintService printService;

    private int roomNumber;
    private byte roomCapacity;
    private boolean availableForLiving;
    private boolean isMaleOnly;

    public CreateRoomFormHandler(FormHandler formHandler, RoomInputValidationService roomInputValidationService,
                                 PrintService printService) {
        this.formHandler = formHandler;
        this.roomInputValidationService = roomInputValidationService;
        this.printService = printService;
    }

    public CreateRoomFormHandler handleRoomNumber() {
        roomNumber = formHandler.handleInputString(
                printService::printInputRoomNumberRequestMessage,
                Integer::parseInt,
                roomInputValidationService::validateNumber);
        return this;
    }

    public CreateRoomFormHandler handleRoomCapacity() {
        roomCapacity = formHandler.handleInputString(
                printService::printInputDormitoryCapacityRequestMessage,
                Byte::parseByte,
                roomInputValidationService::validateNumber);
        return this;
    }

    public CreateRoomFormHandler handleAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                roomInputValidationService::validateAvailableForLiving);

        availableForLiving = availableForLivingInput.equalsIgnoreCase("y");
        return this;
    }

    public CreateRoomFormHandler handleGender() {
        String maleOnlyInput = formHandler.handleInputString(
                printService::printInputGenderRoomRequestMessage,
                Function.identity(),
                roomInputValidationService::validateGender);

        isMaleOnly = maleOnlyInput.equalsIgnoreCase("m");
        return this;
    }

    public CreateRoomDto createRoomDto() {
        return new CreateRoomDto(roomNumber, roomCapacity, availableForLiving, isMaleOnly);
    }
}
