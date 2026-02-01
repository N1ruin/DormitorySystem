package by.niruin.dormitorySystem.domain.context;

import by.niruin.dormitorySystem.domain.model.User;

public class ApplicationContext {
    private User activeUser;

    public ApplicationContext() {
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User newActiveUser) {
        activeUser = newActiveUser;
    }
}
