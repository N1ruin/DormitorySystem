package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.dormitory.*;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class DormitoryMenu implements Menu {
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final DormitoryService dormitoryService;
    private final SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler;
    private final CreateDormitoryFormHandler createDormitoryFormHandler;
    private final DeleteDormitoryFormHandler deleteDormitoryFormHandler;
    private final UpdateDormitoryFormHandler updateDormitoryFormHandler;
    private final DormitoryInfoFormHandler dormitoryInfoFormHandler;
    private final Logger logger = LoggerFactory.getLogger(DormitoryMenu.class);

    public DormitoryMenu(PrintService printService, InputService inputService, MenuFactory menuFactory, DormitoryService dormitoryService, SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler, CreateDormitoryFormHandler createDormitoryFormHandler, DeleteDormitoryFormHandler deleteDormitoryFormHandler, UpdateDormitoryFormHandler updateDormitoryFormHandler, DormitoryInfoFormHandler dormitoryInfoFormHandler) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.dormitoryService = dormitoryService;
        this.selectCurrentDormitoryFormHandler = selectCurrentDormitoryFormHandler;
        this.createDormitoryFormHandler = createDormitoryFormHandler;
        this.deleteDormitoryFormHandler = deleteDormitoryFormHandler;
        this.updateDormitoryFormHandler = updateDormitoryFormHandler;
        this.dormitoryInfoFormHandler = dormitoryInfoFormHandler;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(MenuItemUtil.buildMenu(DormitoryMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(DormitoryMenuItem.class, Integer.parseInt(userInput));
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
            case GET_SORTED_DORMITORIES -> nextMenu = menuFactory.createSelectSortDormitoriesOrderMenu();
            case GET_DORMITORY_INFO -> getDormitoryInfo();
            case GO_BACK -> nextMenu = menuFactory.createMainMenu();
        }
        return nextMenu;
    }

    private void selectCurrentDormitory() {
        var dto = selectCurrentDormitoryFormHandler.handleDormitoryNumber().createDto();

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
        var dto = createDormitoryFormHandler
                .handleDormitoryNumber()
                .handleDormitoryCapacity()
                .handleAvailable()
                .createDormitoryDto();

        try {
            dormitoryService.createDormitory(dto);
            printService.printDormitoryCreatedSuccessfulMessage();
            logger.info(DORMITORY_CREATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_CREATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void deleteDormitory() {
        var dto = deleteDormitoryFormHandler.handleDormitoryNumber().createDto();

        try {
            dormitoryService.deleteDormitory(dto);
            printService.printDormitoryDeletedSuccessfulMessage();
            logger.info(DORMITORY_DELETED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_DELETED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void updateDormitory() {
        var dto = updateDormitoryFormHandler
                .handleNumber()
                .handleAvailable()
                .createDto();
        try {
            dormitoryService.updateDormitory(dto);
            printService.printDormitoryUpdatedSuccessfulMessage();
            logger.info(DORMITORY_UPDATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_UPDATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void getDormitoryInfo() {
        var dto = dormitoryInfoFormHandler.handleDormitoryNumber().createDto();

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
