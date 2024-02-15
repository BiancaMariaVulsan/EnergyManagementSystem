package ro.tuc.ds2020.contracts.requests;


public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private int roleId;
    private String password;

    public SignUpRequest(String firstName, String lastName, String userName, String email, int roleId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.roleId = roleId;
        this.password = password;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
