package com.test.entity;

public enum AuditableEntityStatus {
    CREATED("Created"),
    UPDATED("Updated"),
    DELETED("Deleted");

    private String displayName;

    AuditableEntityStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return name();
    }
}
