package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Student;

import java.util.UUID;

public interface StudentRepository extends Repository<Student<UUID>, UUID> {

}
