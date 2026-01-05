
import by.niruin.dormitorySystem.Application;
import by.niruin.dormitorySystem.infrastructure.dependencyContainer.DependencyContainer;
import by.niruin.dormitorySystem.infrastructure.loader.RepositoryDataLoader;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;

import java.util.Arrays;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info(APP_STARTED_LOG);
        DependencyContainer dc = new DependencyContainer("by.niruin.dormitorySystem");
        try {
            Application application = dc.getObject(Application.class);
            application.run();
        } catch (Exception e) {
            logger.error(APP_ERROR_LOG);
            logger.error(Arrays.toString(e.getStackTrace()));
            logger.info(SAVING_DATA_LOG);
            RepositoryDataLoader repositoryDataLoader = dc.getObject(RepositoryDataLoader.class);
            repositoryDataLoader.persistData();
        } finally {
            logger.info(APP_EXIT_LOG);
        }
    }
}
