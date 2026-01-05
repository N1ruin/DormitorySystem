package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum MainMenuItem {
    @MenuItem(itemAction = "System administrator menu", allowedRoles = Role.SYSTEM_ADMIN)
    SYSTEM_ADMIN_MENU,
    @MenuItem(itemAction = "Select current university", allowedRoles = Role.SYSTEM_ADMIN)
    SELECT_CURRENT_UNIVERSITY_MENU,
    @MenuItem(itemAction = "Select current dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SELECT_CURRENT_DORMITORY_MENU,
    @MenuItem(itemAction = "Rooms", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    ROOMS,
    @MenuItem(itemAction = "Dormitories", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    DORMITORIES,
    @MenuItem(itemAction = "Students", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    STUDENTS,
    @MenuItem(itemAction = "Universities", allowedRoles = Role.SYSTEM_ADMIN)
    UNIVERSITIES,
    @MenuItem(itemAction = "Log out")
    LOG_OUT
}


