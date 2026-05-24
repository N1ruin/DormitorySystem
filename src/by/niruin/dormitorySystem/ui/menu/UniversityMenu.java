package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.statistic.UniversityStatisticService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.DormitoryFormHandlerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.UniversityFormHandlerFactory;
import by.niruin.dormitorySystem.ui.menu.item.UniversityMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class UniversityMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(UniversityMenu.class);
    private final InputService inputService;
    private final PrintService printService;
    private final UniversityService universityService;
    private final DormitoryService dormitoryService;
    private final MenuLocator menuLocator;
    private final MenuItemService menuItemService;
    private final UniversityFormHandlerFactory universityFormHandlerFactory;
    private final DormitoryFormHandlerFactory dormitoryFormHandlerFactory;
    private final UniversityStatisticService universityStatisticService;

    public UniversityMenu(InputService inputService, PrintService printService, UniversityService universityService,
                          DormitoryService dormitoryService, MenuLocator menuLocator, MenuItemService menuItemService,
                          UniversityFormHandlerFactory universityFormHandlerFactory,
                          DormitoryFormHandlerFactory dormitoryFormHandlerFactory,
                          UniversityStatisticService universityStatisticService) {
        this.inputService = inputService;
        this.printService = printService;
        this.universityService = universityService;
        this.dormitoryService = dormitoryService;
        this.menuLocator = menuLocator;
        this.menuItemService = menuItemService;
        this.universityFormHandlerFactory = universityFormHandlerFactory;
        this.dormitoryFormHandlerFactory = dormitoryFormHandlerFactory;
        this.universityStatisticService = universityStatisticService;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(menuItemService.buildMenu(UniversityMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(UniversityMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return executeMenuItem(item);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu executeMenuItem(UniversityMenuItem item) {
        Menu nextMenu = this;
        switch (item) {
            case SELECT_CURRENT_UNIVERSITY -> selectCurrentUniversity();
            case CREATE_UNIVERSITY -> createUniversity();
            case DELETE_UNIVERSITY -> deleteUniversity();
            case UPDATE_UNIVERSITY -> updateUniversity();
            case GET_SORTED_UNIVERSITIES -> nextMenu = menuLocator.getMenu(SelectSortUniversitiesOrderMenu.class);
            case GET_UNIVERSITY_INFO -> getUniversityInfo();
            case GET_UNIVERSITY_STATISTICS -> getUniversityStatistic();
            case GO_BACK -> nextMenu = menuLocator.getMenu(MainMenu.class);
        }
        return nextMenu;
    }

    private void selectCurrentUniversity() {
        var dto = universityFormHandlerFactory.getSelectCurrentUniversityFormHandler()
                .inputUniversityName()
                .createDto();

        try {
            universityService.updateCurrentUniversity(dto);
            printService.printCurrentUniversitySelectedMessage();
            updateCurrentDormitory();
            logger.info(CURRENT_UNIVERSITY_SELECT_SUCCESSFUL_LOG);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printService.printExceptionMessage(e);
            logger.info(CURRENT_UNIVERSITY_SELECT_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void updateCurrentDormitory() {
        try {
            if (!universityService.isCurrentUniversityHasDormitories()) {
                setNullableDormitory();
            } else {
                var dto = dormitoryFormHandlerFactory.getSelectCurrentDormitoryFormHandler()
                        .inputDormitoryNumber()
                        .createDto();

                dormitoryService.updateCurrentDormitory(dto);
                printService.printCurrentDormitorySelectedMessage();
                logger.info(CURRENT_DORMITORY_SELECT_SUCCESSFUL_LOG);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printService.printExceptionMessage(e);
            logger.info(CURRENT_DORMITORY_SELECT_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void setNullableDormitory() {
        printService.printUniversityHasNoDormitoriesMessage();
        printService.printCreateDormitoryRequestMessage();

        dormitoryService.updateCurrentDormitory(null);
    }

    private void createUniversity() {
        var dto = universityFormHandlerFactory.getCreateUniversityFormHandler()
                .inputUniversityName()
                .inputStudyDuration()
                .createDto();
        try {
            universityService.create(dto);
            printService.printUniversityCreatedSuccessfulMessage();
            logger.info(UNIVERSITY_CREATED_SUCCESSFUL_LOG.formatted(dto.universityName()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_CREATED_FAIL_LOG.formatted(dto.universityName()));
            logger.info(e.getMessage());
        }
    }

    private void deleteUniversity() {
        var dto = universityFormHandlerFactory.getDeleteUniversityFormHandler()
                .inputUniversityNumber()
                .createDto();

        try {
            universityService.delete(dto);
            printService.printUniversityDeletedSuccessfulMessage();
            logger.info(UNIVERSITY_DELETED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_DELETED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void updateUniversity() {
        var dto = universityFormHandlerFactory.getUpdateUniversityFormHandler()
                .inputUniversityNumber()
                .inputAvailable()
                .createDto();

        try {
            universityService.update(dto);
            printService.printUniversityUpdatedSuccessfulMessage();
            logger.info(UNIVERSITY_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getUniversityInfo() {
        var dto = universityFormHandlerFactory.getUniversityInfoFormHandler()
                .inputUniversityNumber()
                .createDto();

        try {
            String universityInfo = universityService.getUniversityInfo(dto);
            printService.printUniversityInfo(universityInfo);
            logger.info(UNIVERSITY_INFO_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_INFO_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getUniversityStatistic() {
        try {
            String statistics = universityStatisticService.getCurrentUniversityStatistics();
            printService.printStatistics(statistics);
            logger.info(UNIVERSITY_STATISTICS_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_STATISTICS_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }
}
