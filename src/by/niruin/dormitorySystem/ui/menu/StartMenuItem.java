package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.ui.annotation.MenuItem;

public enum StartMenuItem {
    @MenuItem(itemAction = "Sign in")
    AUTH_MENU,
    @MenuItem(itemAction = "Sign up")
    REGISTRATION_MENU,
    @MenuItem(itemAction = "Exit application")
    EXIT
}
