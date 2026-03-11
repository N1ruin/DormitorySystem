package by.niruin.dormitorySystem.ui.menu.item;

import by.niruin.dormitorySystem.domain.model.Role;

public enum RoomMenuItem {
    @MenuItem(itemAction = "Create room", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    CREATE_ROOM,
    @MenuItem(itemAction = "Delete room", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    DELETE_ROOM,
    @MenuItem(itemAction = "Update room", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    UPDATE_ROOM,
    @MenuItem(itemAction = "Get room list", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    GET_SORTED_ROOMS,
    @MenuItem(itemAction = "Get room info", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    GET_ROOM_INFO,
    @MenuItem(itemAction = "Get students without room", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    GET_INHABILITIES_STUDENTS,
    @MenuItem(itemAction = "Go back", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    GO_BACK
}
