package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.item.SelectSortStudentsOrderMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import java.util.Comparator;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortStudentsOrderMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(SelectSortStudentsOrderMenu.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final StudentService studentService;
    private final MenuItemService menuItemService;

    public SelectSortStudentsOrderMenu(PrintService printService, InputService inputService, MenuFactory menuFactory,
                                       StudentService studentService, MenuItemService menuItemService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.studentService = studentService;
        this.menuItemService = menuItemService;
    }


    @Override
    public void display() {
        printService.printMenu(menuItemService.buildMenu(SelectSortStudentsOrderMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(SelectSortStudentsOrderMenuItem.class, Integer.parseInt(userInput));
            String sortedStudentsInfo = studentService.getStudentsSortedInfo(getStudentComparator(item));
            printService.printSortedStudentsInfo(sortedStudentsInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.getMenu(StudentMenu.class);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return menuFactory.getMenu(StudentMenu.class);
        }
    }

    private Comparator<Student> getStudentComparator(SelectSortStudentsOrderMenuItem item) {
        return switch (item) {
            case SORT_BY_NAME -> Comparator.comparing(student -> student.getFullName().getFullNameString());
            case SORT_BY_NAME_DESC ->
                    Comparator.comparing((Student student) -> student.getFullName().getFullNameString()).reversed();
            case SORT_BY_GENDER_MALE_FIRST -> Comparator.comparing(Student::getGender);
            case SORT_BY_GENDER_FEMALE_FIRST -> Comparator.comparing(Student::getGender).reversed();
            case SORT_BY_START_EDUCATION_DATE -> Comparator.comparing(Student::getStartEducationDate);
            case SORT_BY_START_EDUCATION_DATE_DESC -> Comparator.comparing(Student::getStartEducationDate).reversed();
            case SORT_BY_END_EDUCATION_DATE -> Comparator.comparing(Student::getEndingEducationDate);
            case SORT_BY_END_EDUCATION_DATE_DESC -> Comparator.comparing(Student::getEndingEducationDate).reversed();
        };
    }
}
