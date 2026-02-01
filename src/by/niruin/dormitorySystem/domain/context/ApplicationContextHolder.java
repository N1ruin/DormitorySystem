package by.niruin.dormitorySystem.domain.context;

public class ApplicationContextHolder {
    private static ApplicationContext applicationContext;

    private ApplicationContextHolder() {
    }

    public static ApplicationContext getContext() {
        if(applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }
}
