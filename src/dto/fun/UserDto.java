package dto.fun;

import dto.Dto;

public class UserDto implements Dto {
    private String username;
    private String password;
    private Boolean isAdmin;

    // Constructors
    public UserDto(String username, String password, Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public int getId() {
        return 0;
    }
}
