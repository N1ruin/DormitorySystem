package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.CreateDormitoryDto;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

@Component
public class CreateDormitoryFormHandler {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService validationService;
    private final PrintService printService;

    private int dormitoryNumber;
    private byte dormitoryCapacity;
    private boolean availableForLiving;

    public CreateDormitoryFormHandler(FormHandler formHandler, DormitoryInputValidationService validationService,
                                      PrintService printService) {
        this.formHandler = formHandler;
        this.validationService = validationService;
        this.printService = printService;
    }

    public CreateDormitoryFormHandler handleDormitoryNumber() {
        dormitoryNumber = formHandler.handleInputString(
                printService::printInputDormitoryNumberRequestMessage,
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public CreateDormitoryFormHandler handleDormitoryCapacity() {
        dormitoryCapacity = formHandler.handleInputString(
                printService::printInputDormitoryCapacityRequestMessage,
                Byte::parseByte,
                validationService::validateNumber);
        return this;
    }

    public CreateDormitoryFormHandler handleAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                validationService::validateAvailableForLiving);

        availableForLiving = availableForLivingInput.equalsIgnoreCase("y");
        return this;
    }

    public CreateDormitoryDto createDormitoryDto() {
        return new CreateDormitoryDto(dormitoryNumber, dormitoryCapacity, availableForLiving);
    }
}
