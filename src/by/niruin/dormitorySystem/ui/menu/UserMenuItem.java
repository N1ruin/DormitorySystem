package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum UserMenuItem {
    @MenuItem(itemAction = "Create user", allowedRoles = {Role.SYSTEM_ADMIN})
    CREATE_USER,
    @MenuItem(itemAction = "Delete user", allowedRoles = {Role.SYSTEM_ADMIN})
    DELETE_USER,
    @MenuItem(itemAction = "Update user", allowedRoles = {Role.SYSTEM_ADMIN})
    UPDATE_USER,
    @MenuItem(itemAction = "Get users list", allowedRoles = {Role.SYSTEM_ADMIN})
    GET_SORTED_USERS,
    @MenuItem(itemAction = "Get user info", allowedRoles = {Role.SYSTEM_ADMIN})
    GET_USER_INFO,
    @MenuItem(itemAction = "Go back", allowedRoles = {Role.SYSTEM_ADMIN})
    GO_BACK
}
