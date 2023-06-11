package model;

public class LoginDto {
    private String email;
    private String password;
    private int role_id;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public LoginDto(String email, String password, int role_id) {
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }
}
