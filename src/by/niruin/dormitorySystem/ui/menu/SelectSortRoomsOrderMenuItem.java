package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum SelectSortRoomsOrderMenuItem {
    @MenuItem(itemAction = "Sort by numberFromList in ascending order",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_NUMBER,
    @MenuItem(itemAction = "Sort by numberFromList in descending order",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_NUMBER_DESC,
    @MenuItem(itemAction = "Sort by numberFromList of available seats in ascending order",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_FREE_QUANTITY,
    @MenuItem(itemAction = "Sort by numberFromList of available seats in descending order",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_FREE_QUANTITY_DESC,
    @MenuItem(itemAction = "Sort by gender - men's rooms first",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_GENDER_MALE_FIRST,
    @MenuItem(itemAction = "Sort by gender - women's rooms first",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_GENDER_FEMALE_FIRST,
    @MenuItem(itemAction = "Sort by affordability - affordable first",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_AVAILABLE_FOR_LIVING,
    @MenuItem(itemAction = "Sort by affordability - unavailable first",
            allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SORT_BY_AVAILABLE_FOR_LIVING_DESC,
}
