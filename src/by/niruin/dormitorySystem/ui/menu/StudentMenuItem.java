package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum StudentMenuItem {
    @MenuItem(itemAction = "Create student", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    CREATE_STUDENT,
    @MenuItem(itemAction = "Delete student", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    DELETE_STUDENT,
    @MenuItem(itemAction = "Update student", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    UPDATE_STUDENT,
    @MenuItem(itemAction = "Get students list", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_SORTED_STUDENTS,
    @MenuItem(itemAction = "Get student info", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_STUDENT_INFO,
    @MenuItem(itemAction = "Get students without dormitory", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    GET_STUDENTS_WITHOUT_DORMITORY,
    @MenuItem(itemAction = "Distribute students to dormitories", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN})
    DISTRIBUTE_STUDENTS_TO_DORMITORIES,
    @MenuItem(itemAction = "Distribute students to rooms", allowedRoles = {Role.SYSTEM_ADMIN, Role.DORMITORY_ADMIN})
    DISTRIBUTE_STUDENTS_TO_ROOMS,
    @MenuItem(itemAction = "Go back", allowedRoles = {Role.SYSTEM_ADMIN, Role.UNIVERSITY_ADMIN, Role.DORMITORY_ADMIN})
    GO_BACK
}
