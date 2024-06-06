package com.example.demo.entity;

public enum RoleKey {
    USER("User", 1),
    ADMIN("Admin", 2);

    private final String name;
    private final Integer index;

    RoleKey(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }
}
