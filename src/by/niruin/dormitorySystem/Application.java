package by.niruin.dormitorySystem;

import by.niruin.dormitorySystem.randomDataGenerator.RandomDataGeneratorFacade;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.loader.RepositoryDataLoader;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.MenuDispatcher;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

@Component
public class Application {
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    private final RepositoryDataLoader repositoryDataLoader;
    private final MenuDispatcher menuDispatcher;
    private final RandomDataGeneratorFacade dataGeneratorFacade;

    public Application(RepositoryDataLoader repositoryDataLoader, MenuDispatcher menuDispatcher,
                       RandomDataGeneratorFacade dataGeneratorFacade) {
        this.repositoryDataLoader = repositoryDataLoader;
        this.menuDispatcher = menuDispatcher;
        this.dataGeneratorFacade = dataGeneratorFacade;
    }

    public void run() {
        logger.info(START_LOADING_DATA_LOG);
        try {
            dataGeneratorFacade.generateTestData();
        } catch (FileNotFoundException e) {
            logger.error(TEST_ENTITIES_CREATE_ERROR_LOG);
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
        repositoryDataLoader.loadData();
        menuDispatcher.dispatch();
        repositoryDataLoader.persistData();
        logger.info(APP_DATA_SAVED_LOG);
    }
}
