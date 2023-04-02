package com.g31.demo.model;

import lombok.Getter;

/**
 * @Description:
 */
@Getter
public enum RoleType {
    USER("Normal User", ""),
    ADMIN("Admin", "The admin.");

    private final String name;
    private final String description;

    RoleType(String value, String description) {
        this.name = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
