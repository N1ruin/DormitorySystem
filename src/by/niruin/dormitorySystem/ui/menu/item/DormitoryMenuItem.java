package by.niruin.dormitorySystem.ui.menu.item;

import by.niruin.dormitorySystem.domain.model.Role;

public enum DormitoryMenuItem {
    @MenuItem(itemAction = "Select current dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    SELECT_CURRENT_DORMITORY,
    @MenuItem(itemAction = "Create dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    CREATE_DORMITORY,
    @MenuItem(itemAction = "Delete dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    DELETE_DORMITORY,
    @MenuItem(itemAction = "Update dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    UPDATE_DORMITORY,
    @MenuItem(itemAction = "Get dormitories list", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_SORTED_DORMITORIES,
    @MenuItem(itemAction = "Get dormitory info", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_DORMITORY_INFO,
    @MenuItem(itemAction = "Go back", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GO_BACK
}
