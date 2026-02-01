package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.dormitory.SelectCurrentDormitoryFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.university.*;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class UniversityMenu implements Menu {
    private final InputService inputService;
    private final PrintService printService;
    private final UniversityService universityService;
    private final DormitoryService dormitoryService;
    private final MenuFactory menuFactory;
    private final SelectCurrentUniversityFormHandler selectCurrentUniversityFormHandler;
    private final SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler;
    private final CreateUniversityFormHandler createUniversityFormHandler;
    private final DeleteUniversityFormHandler deleteUniversityFormHandler;
    private final UpdateUniversityFormHandler updateUniversityFormHandler;
    private final UniversityInfoFormHandler universityInfoFormHandler;
    private final Logger logger = LoggerFactory.getLogger(UniversityMenu.class);

    public UniversityMenu(InputService inputService, PrintService printService, UniversityService universityService, DormitoryService dormitoryService, MenuFactory menuFactory, SelectCurrentUniversityFormHandler selectCurrentUniversityFormHandler, SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler, CreateUniversityFormHandler createUniversityFormHandler, DeleteUniversityFormHandler deleteUniversityFormHandler, UpdateUniversityFormHandler updateUniversityFormHandler, UniversityInfoFormHandler universityInfoFormHandler) {
        this.inputService = inputService;
        this.printService = printService;
        this.universityService = universityService;
        this.dormitoryService = dormitoryService;
        this.menuFactory = menuFactory;
        this.selectCurrentUniversityFormHandler = selectCurrentUniversityFormHandler;
        this.selectCurrentDormitoryFormHandler = selectCurrentDormitoryFormHandler;
        this.createUniversityFormHandler = createUniversityFormHandler;
        this.deleteUniversityFormHandler = deleteUniversityFormHandler;
        this.updateUniversityFormHandler = updateUniversityFormHandler;
        this.universityInfoFormHandler = universityInfoFormHandler;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(MenuItemUtil.buildMenu(UniversityMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(UniversityMenuItem.class, Integer.parseInt(userInput));
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
            case GET_SORTED_UNIVERSITIES -> nextMenu = menuFactory.createSelectSortUniversitiesOrderMenu();
            case GET_UNIVERSITY_INFO -> getUniversityInfo();
            case GET_UNIVERSITY_STATISTICS -> getUniversityStatistic();
            case GO_BACK -> nextMenu = menuFactory.createMainMenu();
        }
        return nextMenu;
    }

    private void selectCurrentUniversity() {
        var dto = selectCurrentUniversityFormHandler.handleUniversityName().createDto();

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
                var dto = selectCurrentDormitoryFormHandler.handleDormitoryNumber().createDto();

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
        var dto = createUniversityFormHandler
                .handleUniversityName()
                .handleStudyDuration()
                .createDto();
        try {
            universityService.createUniversity(dto);
            printService.printUniversityCreatedSuccessfulMessage();
            logger.info(UNIVERSITY_CREATED_SUCCESSFUL_LOG.formatted(dto.universityName()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_CREATED_FAIL_LOG.formatted(dto.universityName()));
            logger.info(e.getMessage());
        }
    }

    private void deleteUniversity() {
        var dto = deleteUniversityFormHandler
                .handleUniversityNumber()
                .createDto();

        try {
            universityService.deleteUniversity(dto);
            printService.printUniversityDeletedSuccessfulMessage();
            logger.info(UNIVERSITY_DELETED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_DELETED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void updateUniversity() {
        var dto = updateUniversityFormHandler
                .handleNumber()
                .handleAvailable()
                .createDto();

        try {
            universityService.updateUniversity(dto);
            printService.printUniversityUpdatedSuccessfulMessage();
            logger.info(UNIVERSITY_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getUniversityInfo() {
        var dto = universityInfoFormHandler
                .handleUniversityNumber()
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
            String statistics = universityService.getCurrentUniversityStatistics();
            printService.printStatistics(statistics);
            logger.info(UNIVERSITY_STATISTICS_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(UNIVERSITY_STATISTICS_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }
}
