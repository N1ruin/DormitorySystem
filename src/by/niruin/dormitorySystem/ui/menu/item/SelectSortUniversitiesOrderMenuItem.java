package by.niruin.dormitorySystem.ui.menu.item;

import by.niruin.dormitorySystem.domain.model.Role;

public enum SelectSortUniversitiesOrderMenuItem {
    @MenuItem(itemAction = "Sort by name in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_NAME,
    @MenuItem(itemAction = "Sort by name in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_NAME_DESC,
    @MenuItem(itemAction = "Sort by study duration in ascending order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_STUDY_DURATION,
    @MenuItem(itemAction = "Sort by study duration in descending order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_STUDY_DURATION_DESC,
}
