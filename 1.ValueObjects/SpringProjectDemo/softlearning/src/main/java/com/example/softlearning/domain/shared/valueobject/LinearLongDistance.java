package com.example.softlearning.domain.shared.valueobject;

public class LinearLongDistance implements Comparable<LinearLongDistance> {

    private final long value;
    private final LinearLongDistanceUnit unit;

    private LinearLongDistance(long value, LinearLongDistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static LinearLongDistance of(long value, LinearLongDistanceUnit unit) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("Distance value cannot be negative");
        }
        return new LinearLongDistance(value, unit);
    }

    public long getValue() {
        return value;
    }

    public long getValueTo(LinearLongDistanceUnit targetUnit) {
        // Convert the value to the target unit
        long valueInKilometers = unit.toKilometers(value);
        return targetUnit.fromKilometers(valueInKilometers);
    }

    public LinearLongDistance toUnit(LinearLongDistanceUnit targetUnit) {
        long valueInKilometers = unit.toKilometers(value);
        long convertedValue = targetUnit.fromKilometers(valueInKilometers);
        
        return new LinearLongDistance(convertedValue, targetUnit);
    }

    public LinearLongDistance add(LinearLongDistance other) {
        long thisInKilometers = unit.toKilometers(value);
        long otherInKilometers = other.unit.toKilometers(other.value);
        long sumInKilometers = thisInKilometers + otherInKilometers;
        long sumInOriginalUnit = unit.fromKilometers(sumInKilometers);

        return new LinearLongDistance(sumInOriginalUnit, unit);
    }

    public LinearLongDistance diff(LinearLongDistance other) {
        long thisInKilometers = unit.toKilometers(value);
        long otherInKilometers = other.unit.toKilometers(other.value);
        long differenceInKilometers = thisInKilometers - otherInKilometers;
        long differenceInOriginalUnit = unit.fromKilometers(differenceInKilometers);

        return new LinearLongDistance(differenceInOriginalUnit, unit);
    }

    public LinearLongDistanceUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.symbol();
    }

    @Override
    public int compareTo(LinearLongDistance other) {
        long thisInKilometers = unit.toKilometers(value);
        long otherInKilometers = other.unit.toKilometers(other.value);

        return Long.compare(thisInKilometers, otherInKilometers);
    }
}