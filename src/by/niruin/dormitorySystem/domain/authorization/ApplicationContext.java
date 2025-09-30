package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;

import java.time.LocalDateTime;

public class ApplicationContext {
    private static User activeUser;
    private static LocalDateTime lastDataUpdate;
    private static boolean dataLoaded = false;

    public ApplicationContext() {
    }

    public static boolean isDataLoaded() {
        return dataLoaded;
    }

    public static void setDataLoaded(boolean dataLoaded) {
        ApplicationContext.dataLoaded = dataLoaded;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User newActiveUser) {
        activeUser = newActiveUser;
    }

    public LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(LocalDateTime dateTime) {
        lastDataUpdate = dateTime;
    }
}
