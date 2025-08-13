package by.niruin.dormitorySystem.domain.idGenerator;

public class UserIdGenerator implements IdGenerator {
    private long nextUserId = 0;

    public long getNextId() {
        return nextUserId++;
    }

    @Override
    public void setNextId(long nextId) {
        nextUserId = nextId;
    }
}
