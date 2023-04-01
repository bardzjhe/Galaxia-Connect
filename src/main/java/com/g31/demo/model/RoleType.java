package com.g31.demo.model;

/**
 * @Description:
 */
public enum RoleType {
    USER("User", ""),
    ADMIN("Admin", "The admin is authorized to update, delete user information. ");

    private final String role;
    private final String description;

    private RoleType(String value, String description) {
        this.role = value;
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }
}
