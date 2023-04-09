package com.g31.demo.model;

import lombok.Getter;

/**
 * @author shuang.kou
 */

@Getter
public enum RoleType {
    USER("USER", "Normal User"),
    ADMIN("ADMIN", "Admin");
    private final String name;
    private final String description;

    RoleType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
