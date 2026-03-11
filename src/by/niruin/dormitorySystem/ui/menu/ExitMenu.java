package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.ui.menu.item.ExitMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

public class ExitMenu implements Menu {
    private final MenuItemService menuItemService;

    public ExitMenu(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @Override
    public void display() {
        menuItemService.buildMenu(ExitMenuItem.class);
    }

    @Override
    public Menu handleInput() {
        return this;
    }
}
