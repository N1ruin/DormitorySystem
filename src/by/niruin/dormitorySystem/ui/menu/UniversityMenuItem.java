package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum UniversityMenuItem {
    @MenuItem(itemAction = "Select current university", allowedRoles = Role.SYSTEM_ADMIN)
    SELECT_CURRENT_UNIVERSITY,
    @MenuItem(itemAction = "Create university", allowedRoles = {Role.SYSTEM_ADMIN})
    CREATE_UNIVERSITY,
    @MenuItem(itemAction = "Delete university", allowedRoles = {Role.SYSTEM_ADMIN})
    DELETE_UNIVERSITY,
    @MenuItem(itemAction = "Update university", allowedRoles = {Role.SYSTEM_ADMIN})
    UPDATE_UNIVERSITY,
    @MenuItem(itemAction = "Get universities list", allowedRoles = {Role.SYSTEM_ADMIN})
    GET_SORTED_UNIVERSITIES,
    @MenuItem(itemAction = "Get university info", allowedRoles = {Role.SYSTEM_ADMIN})
    GET_UNIVERSITY_INFO,
    @MenuItem(itemAction = "Get university statistics", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_UNIVERSITY_STATISTICS,
    @MenuItem(itemAction = "Go back", allowedRoles = {Role.SYSTEM_ADMIN})
    GO_BACK
}
