package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.Objects;
import java.util.UUID;

public class User implements Identity {
    private final UUID id;
    private String login;
    private int passwordHash;
    private Role role;
    private final FullName fullName;
    private final Gender gender;
    private UUID universityId;
    private UUID dormitoryId;

    public User(UUID id, String login, String password, Role role, String firstName,
                String lastName, String fatherName, Gender gender, UUID universityID, UUID dormitoryId) {
        this.id = id;
        this.login = login;
        this.passwordHash = password.hashCode();
        this.role = role;
        this.fullName = new FullName(firstName, fatherName, lastName);
        this.gender = gender;
        this.universityId = universityID;
        this.dormitoryId = dormitoryId;
    }

    public User(UUID id, String login, Role role, String firstName,
                String lastName, String fatherName, Gender gender, UUID universityID, UUID dormitoryId) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.fullName = new FullName(firstName, fatherName, lastName);
        this.gender = gender;
        this.universityId = universityID;
        this.dormitoryId = dormitoryId;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setLogin(String newLogin) {
        this.login = newLogin;
    }

    public void setPasswordHash(String newPassword) {
        this.passwordHash = newPassword.hashCode();
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUniversityId(UUID universityId) {
        this.universityId = universityId;
    }

    public void setDormitoryId(UUID dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public UUID getUniversityId() {
        return universityId;
    }

    public UUID getDormitoryId() {
        return dormitoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (o != this) {
            return false;
        }

        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
