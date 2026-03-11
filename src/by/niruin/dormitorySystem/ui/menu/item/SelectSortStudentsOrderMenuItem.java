package by.niruin.dormitorySystem.ui.menu.item;

import by.niruin.dormitorySystem.domain.model.Role;

public enum SelectSortStudentsOrderMenuItem {
    @MenuItem(itemAction = "Sort by name in alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_NAME,
    @MenuItem(itemAction = "Sort by name in reverse alphabetical order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_NAME_DESC,
    @MenuItem(itemAction = "Sort by gender - male first", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_GENDER_MALE_FIRST,
    @MenuItem(itemAction = "Sort by gender - female first", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_GENDER_FEMALE_FIRST,
    @MenuItem(itemAction = "Sort by start education date", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_START_EDUCATION_DATE,
    @MenuItem(itemAction = "Sort by start education date in descending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_START_EDUCATION_DATE_DESC,
    @MenuItem(itemAction = "Sort by end education date", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_END_EDUCATION_DATE,
    @MenuItem(itemAction =  "Sort by end education date in descending order", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SORT_BY_END_EDUCATION_DATE_DESC
}
