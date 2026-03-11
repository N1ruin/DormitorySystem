package by.niruin.dormitorySystem.ui.menu.item;

import by.niruin.dormitorySystem.domain.model.Role;

public enum SelectSortUsersOrderMenuItem {
    @MenuItem(itemAction = "Sort by login in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_LOGIN,
    @MenuItem(itemAction = "Sort by login in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_LOGIN_DESC,
    @MenuItem(itemAction = "Sort by full name in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_FULL_NAME,
    @MenuItem(itemAction = "Sort by full name in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_FULL_NAME_DESC,
    @MenuItem(itemAction = "Sort by gender - male first", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_GENDER_MALE_FIRST,
    @MenuItem(itemAction = "Sort by gender - male last", allowedRoles = {Role.SYSTEM_ADMIN})
    SORT_BY_GENDER_MALE_LAST,
}
