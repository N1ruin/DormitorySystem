package by.niruin.dormitorySystem.domain.model;

import by.niruin.dormitorySystem.domain.repository.Identity;

import java.util.Objects;

public class User<ID> implements Identity<ID> {
    private final ID id;
    private String login;
    private int passwordHash;
    private Role role;
    private final FullName fullName;
    private final Gender gender;

    public User(ID id, String login, String password, Role role, String firstName,
                String lastName, String fatherName, Gender gender) {
        this.id = id;
        this.login = login;
        this.passwordHash = password.hashCode();
        this.role = role;
        this.fullName = new FullName(firstName, lastName, fatherName);
        this.gender = gender;
    }

    @Override
    public ID getId() {
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
