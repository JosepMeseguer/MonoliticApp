package com.example.softlearning.domain.shared.valueobject;

public class CheckedString {
    private final String value;

    private CheckedString(String value, int minLength, int maxLength) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        if (value.trim().length() < minLength) {
            throw new IllegalArgumentException("Value is too short");
        }
        if (value.trim().length() > maxLength) {
            throw new IllegalArgumentException("Value is too long");
        }
        this.value = value.trim();
    }

    public static CheckedString of(String value, int minLength, int maxLength) throws IllegalArgumentException {
        return new CheckedString(value, minLength, maxLength);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CheckedString)) return false;
        CheckedString that = (CheckedString) obj;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}