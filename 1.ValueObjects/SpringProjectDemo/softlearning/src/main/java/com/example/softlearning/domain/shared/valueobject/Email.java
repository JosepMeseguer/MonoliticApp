package com.example.softlearning.domain.shared.valueobject;

import java.util.regex.Pattern;

// Representa un correo electronico en apariencia válido.
public final class Email {

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$");

    private final CheckedString email;

    private Email(CheckedString email) {
        this.email = email;
    }

    public static Email of(String value) throws IllegalArgumentException {

        CheckedString checkedValue = CheckedString.of(value, 10, 50);

        if (!EMAIL_PATTERN.matcher(checkedValue.getValue()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new Email(checkedValue);
    }

    public String getValue() {
        return email.getValue();
    }
}