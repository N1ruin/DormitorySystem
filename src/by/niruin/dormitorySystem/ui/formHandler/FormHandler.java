package by.niruin.dormitorySystem.ui.formHandler;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class FormHandler {
    private final InputService inputService;
    private final PrintService printService;
    private final Logger logger = LoggerFactory.getLogger(FormHandler.class);

    public FormHandler(InputService inputService, PrintService printService) {
        this.inputService = inputService;
        this.printService = printService;
    }

    public <T> T handleInputString(Runnable consoleMessagePrinter, Function<String, T> mappper, Consumer<String> validator) {
        while (true) {
            try {
                consoleMessagePrinter.run();
                String input = inputService.inputLine().trim();
                validator.accept(input);
                return mappper.apply(input);
            } catch (RuntimeException e) {
                printService.printExceptionMessage(e);
                logger.warn(e.getMessage());
            } catch (Exception e) {
                printService.printInvalidInputMessage();
                logger.info(e.getMessage());
            }
        }
    }
}
