package com.example.softlearning.domain.shared.valueobject;

public class LinearDistance implements Comparable<LinearDistance> {

    private final long value;
    private final LinearDistanceUnit unit;

    private LinearDistance(long value, LinearDistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static LinearDistance of(long value, LinearDistanceUnit unit) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("Distance value cannot be negative");
        }
        return new LinearDistance(value, unit);
    }

    public long getValue() {
        return value;
    }

    public long getValueTo(LinearDistanceUnit targetUnit) {
        // Convert the value to the target unit
        long valueInMilliMeters = unit.toMillimeters(value);
        return targetUnit.fromMillimeters(valueInMilliMeters);
    }

    public LinearDistance toUnit(LinearDistanceUnit targetUnit) {
        long valueInMilliMeters = unit.toMillimeters(value);
        long convertedValue = targetUnit.fromMillimeters(valueInMilliMeters);
        return new LinearDistance(convertedValue, targetUnit);
    }

    public LinearDistance add(LinearDistance other) {
        long thisInMilliMeters = unit.toMillimeters(value);
        long otherInMilliMeters = other.unit.toMillimeters(other.value);
        long sumInMilliMeters = thisInMilliMeters + otherInMilliMeters;
        long sumInOriginalUnit = unit.fromMillimeters(sumInMilliMeters);
        return new LinearDistance(sumInOriginalUnit, unit);
    }

    public LinearDistance diff(LinearDistance other) {
        long thisInMilliMeters = unit.toMillimeters(value);
        long otherInMilliMeters = other.unit.toMillimeters(other.value);
        long differenceInMilliMeters = thisInMilliMeters - otherInMilliMeters;
        long differenceInOriginalUnit = unit.fromMillimeters(differenceInMilliMeters);

        return new LinearDistance(differenceInOriginalUnit, unit);
    }

    public LinearDistanceUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.symbol();
    }

    @Override
    public int compareTo(LinearDistance other) {
        long thisInMilliMeters = unit.toMillimeters(value);
        long otherInMilliMeters = other.unit.toMillimeters(other.value);
        return Long.compare(thisInMilliMeters, otherInMilliMeters);
    }
}