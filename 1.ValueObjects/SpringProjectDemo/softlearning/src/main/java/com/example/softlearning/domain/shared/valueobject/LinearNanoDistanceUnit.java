package com.example.softlearning.domain.shared.valueobject;

public enum LinearNanoDistanceUnit {
    NANOMETER(1L, "nm"),
    MICROMETER(1_000L, "µm"),
    MILLIMETER(1_000_000L, "mm");

    private final long nanometersFactor;
    private final String symbol;

    LinearNanoDistanceUnit(long nanometersFactor, String symbol) {
        this.nanometersFactor = nanometersFactor;
        this.symbol = symbol;
    }
 
    long toNanometers(long value) {
        return value * nanometersFactor;
    }

    long fromNanometers(long nmValue) {
        return nmValue / nanometersFactor;
    }

    public String symbol() {
        return symbol;
    }
}