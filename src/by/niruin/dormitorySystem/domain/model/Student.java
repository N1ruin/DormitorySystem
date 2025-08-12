package by.niruin.dormitorySystem.domain.model;

import java.time.LocalDate;

public class Student {
    private final long id;
    private final FullName fullName;
    private final Gender gender;
    private University university;
    private Dormitory dormitory;
    private Room room;
    private int yearOfEntering;
    private final LocalDate deductionDate;

    public Student(long id, FullName fullName, Gender gender, LocalDate deductionDate, int yearOfEntering, Room room, Dormitory dormitory, University university) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.deductionDate = deductionDate;
        this.yearOfEntering = yearOfEntering;
        this.room = room;
        this.dormitory = dormitory;
        this.university = university;
    }

    public long getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public University getUniversity() {
        return university;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public Room getRoom() {
        return room;
    }

    public int getYearOfEntering() {
        return yearOfEntering;
    }

    public LocalDate getDeductionDate() {
        return deductionDate;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setYearOfEntering(int yearOfEntering) {
        this.yearOfEntering = yearOfEntering;
    }
}
