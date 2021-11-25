package dto.fun;

import dto.Dto;

public class UserDto implements Dto {
    private int id;
    private String username;
    private String name;
    private String password;
    private RoleDto role;

    public UserDto(int id, String username, String name, String password, RoleDto role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " / " + role;
    }

    public boolean isAdministrator() {
        return role.getName().equals("administrator");
    }

    public boolean isResponsable() {
        return role.getName().equals("responsable");
    }
}
