package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum SelectSortUniversitiesOrderMenuItem {
    @MenuItem(itemAction = "Sort by universityName in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_NAME,
    @MenuItem(itemAction = "Sort by universityName in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_NAME_DESC,
    @MenuItem(itemAction = "Sort by study duration in ascending order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_STUDY_DURATION,
    @MenuItem(itemAction = "Sort by study duration in descending order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_STUDY_DURATION_DESC,
}
