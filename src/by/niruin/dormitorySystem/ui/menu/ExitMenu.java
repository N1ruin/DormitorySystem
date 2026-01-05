package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.util.MenuItemUtil;

public class ExitMenu implements Menu {
    @Override
    public void display() {
        MenuItemUtil.buildMenu(ExitMenuItem.class);
    }

    @Override
    public Menu handleInput() {
        return this;
    }
}
