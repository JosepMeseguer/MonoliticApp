package com.example.softlearning.domain.shared.valueobject;

public enum WeightUnit {
    GRAM(1L, "g"),
    KILOGRAM(1_000L, "kg"),
    TONNE(1_000_000L, "t");

    private final long gramsFactor;
    private final String symbol;

    WeightUnit(long gramsFactor, String symbol) {
        this.gramsFactor = gramsFactor;
        this.symbol = symbol;
    }

    long toGrams(long value) {
        return value * gramsFactor;
    }

    long fromGrams(long valueInGrams) {
        return valueInGrams / gramsFactor;
    }

    public String symbol() {
        return symbol;
    }
}