package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum SelectSortUsersOrderMenuItem {
    @MenuItem(itemAction = "Sort by login in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_LOGIN,
    @MenuItem(itemAction = "Sort by login in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_LOGIN_DESC,
    @MenuItem(itemAction = "Sort by full universityName in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_FULL_NAME,
    @MenuItem(itemAction = "Sort by full universityName in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_FULL_NAME_DESC,
    @MenuItem(itemAction = "Sort by gender - male first", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_GENDER_MALE_FIRST,
    @MenuItem(itemAction = "Sort by gender - male last", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_GENDER_MALE_LAST,
}
