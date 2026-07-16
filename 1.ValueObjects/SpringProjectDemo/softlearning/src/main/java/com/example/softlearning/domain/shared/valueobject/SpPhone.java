package com.example.softlearning.domain.shared.valueobject;

import java.util.regex.Pattern;

/**
 * Represents a spanish smartphone in the system.
 * with (+34) country code or without it, with or without spaces.
 */
public class SpPhone {
    
    // Regular expression pattern to validate Spanish phone numbers
    private static final Pattern PHONE_PATTERN = 
                            Pattern.compile("^((\\+34[\\s])|(\\(\\+34\\)[\\s]?))?[9|8|7|6][0-9]{2}([0-9]{6}|([\\s][0-9]{3}){2}|([\\s][0-9]{2}){3})$");

    private final CheckedString phoneNumber;

    private SpPhone(CheckedString phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static SpPhone of(String phoneNumber) throws IllegalArgumentException {

        phoneNumber = phoneNumber.replaceAll(" ", ""); // Remove spaces for validation
        
        CheckedString checkedValue = CheckedString.of(phoneNumber, 9, 14);
        
        if (!PHONE_PATTERN.matcher(checkedValue.getValue()).matches()) {
            throw new IllegalArgumentException("Invalid Spanish phone number format");
        }
        return new SpPhone(checkedValue);
    }

    public String getValue() {
        return phoneNumber.getValue();
    }
}