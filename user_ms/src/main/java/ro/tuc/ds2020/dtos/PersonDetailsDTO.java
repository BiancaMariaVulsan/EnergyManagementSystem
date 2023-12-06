package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Role;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonDetailsDTO {
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private Role role;
    private String password;

    public PersonDetailsDTO() {
    }

    public PersonDetailsDTO(int id, String firstName, String lastName, String userName, String email, Role role, String passwordHash) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.role = role;
        this.password = passwordHash;
    }

    public PersonDetailsDTO(String firstName, String lastName, String userName, String email, Role role, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.role = role;
        this.password = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetailsDTO that = (PersonDetailsDTO) o;
        return Objects.equals(id, that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && username.equals(that.username) && email.equals(that.email) && role.equals(that.role) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, email, role, password);
    }
}
