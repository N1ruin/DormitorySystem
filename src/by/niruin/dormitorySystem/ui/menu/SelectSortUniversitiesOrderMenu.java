package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.item.SelectSortUniversitiesOrderMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import java.util.Comparator;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortUniversitiesOrderMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(SelectSortUniversitiesOrderMenu.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final MenuItemService menuItemService;
    private final UniversityService universityService;

    public SelectSortUniversitiesOrderMenu(PrintService printService, InputService inputService,
                                           MenuFactory menuFactory, MenuItemService menuItemService, UniversityService universityService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.menuItemService = menuItemService;
        this.universityService = universityService;
    }

    @Override
    public void display() {
        printService.printMenu(menuItemService.buildMenu(SelectSortUniversitiesOrderMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(SelectSortUniversitiesOrderMenuItem.class, Integer.parseInt(userInput));
            String sortedUniversitiesInfo = universityService.getSortedUniversitiesInfo(getUniversityComparator(item));
            printService.printSortedUniversitiesInfo(sortedUniversitiesInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.getMenu(UniversityMenu.class);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Comparator<University> getUniversityComparator(SelectSortUniversitiesOrderMenuItem item) {
        return switch (item) {
            case SORT_BY_NAME -> Comparator.comparing(University::getName);
            case SORT_BY_NAME_DESC -> Comparator.comparing(University::getName).reversed();
            case SORT_BY_STUDY_DURATION -> Comparator.comparingInt(University::getStudyDuration);
            case SORT_BY_STUDY_DURATION_DESC -> Comparator.comparingInt(University::getStudyDuration).reversed();
        };
    }
}
