package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;

import java.time.LocalDateTime;

public class ApplicationContext {
    private static User activeUser;
    private static LocalDateTime lastDataUpdate;

    public ApplicationContext() {
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User newActiveUser) {
        activeUser = newActiveUser;
    }

    public static LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public static void setLastDataUpdate(LocalDateTime dateTime) {
        lastDataUpdate = dateTime;
    }
}
