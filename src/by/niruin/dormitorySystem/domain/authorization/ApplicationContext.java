package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationContext {
    private User<UUID> activeUser;
    private LocalDateTime lastDataUpdate;
    private boolean dataLoaded;

    public ApplicationContext() {
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }

    public void setDataLoaded(boolean dataLoaded) {
        this.dataLoaded = dataLoaded;
    }

    public User<UUID> getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User<UUID> newActiveUser) {
        activeUser = newActiveUser;
    }

    public LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(LocalDateTime dateTime) {
        lastDataUpdate = dateTime;
    }
}
