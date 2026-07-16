package com.example.softlearning.domain.shared.valueobject;

import java.util.regex.Pattern;

public final class Password {
    //establece los límites de longitud de la contraseña
    private static final int MIN_LENGTH = 12;
    private static final int MAX_LENGTH = 20;

    //expresión regular para validar la fortaleza de la contraseña
    private static final Pattern STRONG_PASSWORD = 
        Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){12,20}$");

    private final CheckedString password;

    private Password(CheckedString password) {        
        this.password = CheckedString.of(password.getValue(), MIN_LENGTH, MAX_LENGTH);
    }

    public static Password of(String value) throws IllegalArgumentException {

        // descarta longitudes de contraseña que no cumplan con los límites establecidos antes de verificar la fortaleza de la contraseña
        CheckedString checkedValue = CheckedString.of(value, MIN_LENGTH, MAX_LENGTH );

        if (!STRONG_PASSWORD.matcher(checkedValue.getValue()).matches()) {
            throw new IllegalArgumentException("Password must be at least 12 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }

        return new Password(checkedValue);
    }

    public String getValue() {
        return password.getValue();
    }
}