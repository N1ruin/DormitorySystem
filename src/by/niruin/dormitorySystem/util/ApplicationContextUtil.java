package by.niruin.dormitorySystem.util;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.UUID;

@Component
public class ApplicationContextUtil {
    public static UUID getCurrentUniversityId() {
        return ApplicationContextHolder.getContext().getActiveUser().getUniversityId();
    }

    public static UUID getCurrentDormitoryId() {
        return ApplicationContextHolder.getContext().getActiveUser().getDormitoryId();
    }

    public static User getActiveUser() {
        return ApplicationContextHolder.getContext().getActiveUser();
    }
}
