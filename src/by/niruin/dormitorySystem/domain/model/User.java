package by.niruin.dormitorySystem.domain.model;

public class User {
    private final long id;
    private String login;
    private int passwordHash;
    private Role role;
    private final FullName fullName;
    private final Gender gender;

    public User(long id, String login, String password, Role role, FullName fullName, Gender gender) {
        this.id = id;
        this.login = login;
        this.passwordHash = password.hashCode();
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
    }

    public User(long id, String login, String password, Role role, String firstName,
                String lastName, String fatherName, Gender gender) {
        this(id, login, password, role, new FullName(firstName, lastName, fatherName), gender);
    }

    public long getId() {
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

    public void setRole(Role role) {
        this.role = role;
    }
}
