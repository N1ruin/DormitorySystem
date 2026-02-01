package by.niruin.dormitorySystem.domain.model.dto.university;

public record UniversityStatisticsDto(String universityName,
                                      int dormitoriesCount,
                                      long availableDormitoriesCount,
                                      long notAvailableDormitoriesCount,
                                      int roomsCountFromAllDormitories,
                                      long availableMaleRoomsCount,
                                      long availableFemaleRoomsCount,
                                      int studentsCount,
                                      int maleStudents,
                                      int femaleStudents,
                                      int studentsInDormitories,
                                      int studentsWithoutDormitory) {
}
