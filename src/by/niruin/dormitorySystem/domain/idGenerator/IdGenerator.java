package by.niruin.dormitorySystem.domain.idGenerator;

public interface IdGenerator {
    long getNextId();
    //при запуске и завершении работы приложения будет загружаться и сохраняться актуальное значение id
    void setNextId(long nextId);
}