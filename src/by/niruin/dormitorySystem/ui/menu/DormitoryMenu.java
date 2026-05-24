package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.DormitoryFormHandlerFactory;
import by.niruin.dormitorySystem.ui.menu.item.DormitoryMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class DormitoryMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryMenu.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuLocator menuLocator;
    private final DormitoryService dormitoryService;
    private final MenuItemService menuItemService;
    private final DormitoryFormHandlerFactory dormitoryFormHandlerFactory;

    public DormitoryMenu(PrintService printService, InputService inputService, MenuLocator menuLocator,
                         DormitoryService dormitoryService, MenuItemService menuItemService,
                         DormitoryFormHandlerFactory dormitoryFormHandlerFactory) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuLocator = menuLocator;
        this.dormitoryService = dormitoryService;
        this.menuItemService = menuItemService;
        this.dormitoryFormHandlerFactory = dormitoryFormHandlerFactory;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(menuItemService.buildMenu(DormitoryMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(DormitoryMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return executeMenuItem(item);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu executeMenuItem(DormitoryMenuItem menuItem) {
        Menu nextMenu = this;
        switch (menuItem) {
            case SELECT_CURRENT_DORMITORY -> selectCurrentDormitory();
            case CREATE_DORMITORY -> createDormitory();
            case DELETE_DORMITORY -> deleteDormitory();
            case UPDATE_DORMITORY -> updateDormitory();
            case GET_SORTED_DORMITORIES -> nextMenu = menuLocator.getMenu(SelectSortDormitoriesOrderMenu.class);
            case GET_DORMITORY_INFO -> getDormitoryInfo();
            case GO_BACK -> nextMenu = menuLocator.getMenu(MainMenu.class);
        }
        return nextMenu;
    }

    private void selectCurrentDormitory() {
        var dto = dormitoryFormHandlerFactory.getSelectCurrentDormitoryFormHandler()
                .inputDormitoryNumber()
                .createDto();

        try {
            dormitoryService.updateCurrentDormitory(dto);
            printService.printCurrentDormitorySelectedMessage();
            logger.info(CURRENT_DORMITORY_SELECT_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(CURRENT_DORMITORY_SELECT_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void createDormitory() {
        var dto = dormitoryFormHandlerFactory.getCreateDormitoryFormHandler()
                .inputDormitoryNumber()
                .inputDormitoryCapacity()
                .inputAvailable()
                .createDormitoryDto();

        try {
            dormitoryService.create(dto);
            printService.printDormitoryCreatedSuccessfulMessage();
            logger.info(DORMITORY_CREATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_CREATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void deleteDormitory() {
        var dto = dormitoryFormHandlerFactory.getDeleteDormitoryFormHandler()
                .inputDormitoryNumber()
                .createDto();

        try {
            dormitoryService.delete(dto);
            printService.printDormitoryDeletedSuccessfulMessage();
            logger.info(DORMITORY_DELETED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_DELETED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void updateDormitory() {
        var dto = dormitoryFormHandlerFactory.getUpdateDormitoryFormHandler()
                .inputNumber()
                .inputAvailable()
                .createDto();
        try {
            dormitoryService.update(dto);
            printService.printDormitoryUpdatedSuccessfulMessage();
            logger.info(DORMITORY_UPDATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_UPDATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void getDormitoryInfo() {
        var dto = dormitoryFormHandlerFactory.getDormitoryInfoFormHandler()
                .inputDormitoryNumber()
                .createDto();

        try {
            String dormitoryInfo = dormitoryService.getDormitoryInfo(dto);
            printService.printDormitoryInfo(dormitoryInfo);
            logger.info(DORMITORY_INFO_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_INFO_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }
}
