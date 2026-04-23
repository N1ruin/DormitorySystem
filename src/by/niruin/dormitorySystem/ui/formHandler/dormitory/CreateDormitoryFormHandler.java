package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.CreateDormitoryDto;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class CreateDormitoryFormHandler {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final PrintService printService;
    private int dormitoryNumber;
    private byte dormitoryCapacity;
    private boolean availableForLiving;

    public CreateDormitoryFormHandler(FormHandler formHandler,
                                      DormitoryInputValidationService dormitoryInputValidationService,
                                      PrintService printService) {
        this.formHandler = formHandler;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.printService = printService;
    }

    public CreateDormitoryFormHandler inputDormitoryNumber() {
        dormitoryNumber = formHandler.handleInputString(
                printService::printInputDormitoryNumberRequestMessage,
                Integer::parseInt,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public CreateDormitoryFormHandler inputDormitoryCapacity() {
        dormitoryCapacity = formHandler.handleInputString(
                printService::printInputDormitoryCapacityRequestMessage,
                Byte::parseByte,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public CreateDormitoryFormHandler inputAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                dormitoryInputValidationService::validateAvailableForLiving);

        availableForLiving = availableForLivingInput.equalsIgnoreCase("y");
        return this;
    }

    public CreateDormitoryDto createDormitoryDto() {
        return new CreateDormitoryDto(dormitoryNumber, dormitoryCapacity, availableForLiving);
    }
}
