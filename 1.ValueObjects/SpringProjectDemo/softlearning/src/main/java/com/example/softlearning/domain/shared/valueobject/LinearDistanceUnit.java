package com.example.softlearning.domain.shared.valueobject;

public enum LinearDistanceUnit {
    
    MILLIMETER(1L, "mm"),
    CENTIMETER(10L, "cm"),
    METER(1_000L, "m"),
    KILOMETER(1_000_000L, "km");

    private final long millimetersFactor;
    private final String symbol;

    LinearDistanceUnit(long millimetersFactor, String symbol) {
        this.millimetersFactor = millimetersFactor;
        this.symbol = symbol;
    }

    long toMillimeters(long value) {
        return value * millimetersFactor;
    }

    long fromMillimeters(long valueInMilliMeters) {
        return valueInMilliMeters / millimetersFactor;
    }

    public String symbol() {
        return symbol;
    }
}