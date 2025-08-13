package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;

import java.time.LocalDateTime;

public class ApplicationContext {
    private User activeUser;
    private LocalDateTime lastDataUpdate;

    public ApplicationContext() {
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(LocalDateTime dateTime) {
        this.lastDataUpdate = dateTime;
    }
}