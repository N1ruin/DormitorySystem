package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum MainMenuItem {
    @MenuItem(itemAction = "Rooms", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    ROOMS,
    @MenuItem(itemAction = "Dormitories", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    DORMITORIES,
    @MenuItem(itemAction = "Students", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    STUDENTS,
    @MenuItem(itemAction = "Universities", allowedRoles = Role.SYSTEM_ADMIN)
    UNIVERSITIES,
    @MenuItem(itemAction = "Users", allowedRoles = Role.SYSTEM_ADMIN)
    USERS,
    @MenuItem(itemAction = "Show account info", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    SHOW_ACCOUNT_INFO,
    @MenuItem(itemAction = "Log out")
    LOG_OUT
}


