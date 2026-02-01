package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum SelectSortDormitoriesOrderMenuItem {
    @MenuItem(itemAction = "Sort by number in ascending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_NUMBER,
    @MenuItem(itemAction = "Sort by number in descending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_NUMBER_DESC,
    @MenuItem(itemAction = "Sort by rooms count in ascending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_ROOM_CAPACITY,
    @MenuItem(itemAction = "Sort by rooms count in descending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_ROOM_CAPACITY_DESC,
    @MenuItem(itemAction = "Sort by affordability - affordable first", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_AVAILABLE_FOR_LIVING,
    @MenuItem(itemAction = "Sort by affordability - unavailable first", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_AVAILABLE_FOR_LIVING_DESC,
}
