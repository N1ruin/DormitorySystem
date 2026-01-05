package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Dormitory;

import java.util.Collection;

public interface DormitoryMapper {
    String mapDormitoriesToString(Collection<Dormitory> dormitories);

    Collection<Dormitory> mapStringToDormitories(String dormitoriesData);
}
