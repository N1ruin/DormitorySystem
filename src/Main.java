
import by.niruin.dormitorySystem.infrastructure.dependencyContainer.DependencyContainer;
import by.niruin.dormitorySystem.infrastructure.loader.RepositoryDataLoader;

public class Main {
    public static void main(String[] args) {
        DependencyContainer dc = new DependencyContainer("by.niruin.dormitorySystem");
        RepositoryDataLoader repositoryDataLoader = dc.getObject(RepositoryDataLoader.class);
        repositoryDataLoader.loadData();
        //цикл приложения
        repositoryDataLoader.persistData();
    }
}
