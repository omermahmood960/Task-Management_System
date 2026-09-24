package app.com.user.dto;

import app.com.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserRequestDTO {
    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "Email field is required")
    private String email;
    @NotBlank(message = "Password field is required")
    private String password;
    @NotNull(message = "Task Id field is required")
    private UUID taskId;
    @NotBlank(message = "User role field is required")
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}