package by.niruin.dormitorySystem.ui.formHandler.room;

import by.niruin.dormitorySystem.domain.model.dto.UpdateRoomDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

@Component
public class UpdateRoomFormHandler {
    private final FormHandler formHandler;
    private final RoomInputValidationService validationService;
    private final PrintService printService;
    private final RoomService roomService;

    private int roomNumber;
    private byte roomCapacity;
    private boolean availableForLiving;
    private boolean gender;

    public UpdateRoomFormHandler(FormHandler formHandler, RoomInputValidationService validationService, PrintService printService, RoomService roomService) {
        this.formHandler = formHandler;
        this.validationService = validationService;
        this.printService = printService;
        this.roomService = roomService;
    }

    public UpdateRoomFormHandler handleNumber() {
        roomNumber = formHandler.handleInputString(
                () -> printService.printRoomNumbers(roomService.getRoomNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public UpdateRoomFormHandler handleCapacity() {
        roomCapacity = formHandler.handleInputString(
                printService::printInputRoomCapacityRequestMessage,
                Byte::parseByte,
                validationService::validateNumber);
        return this;
    }

    public UpdateRoomFormHandler handleAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                validationService::validateAvailableForLiving);
        availableForLiving = availableForLivingInput.equalsIgnoreCase("yes");
        return this;
    }

    public UpdateRoomFormHandler handleGender() {
        String genderInput = formHandler.handleInputString(
                printService::printInputGenderRoomRequestMessage,
                Function.identity(),
                validationService::validateGender);
        gender = genderInput.equalsIgnoreCase("male");
        return this;
    }

    public UpdateRoomDto createDto() {
        return new UpdateRoomDto(roomNumber, roomCapacity, availableForLiving, gender);
    }
}
