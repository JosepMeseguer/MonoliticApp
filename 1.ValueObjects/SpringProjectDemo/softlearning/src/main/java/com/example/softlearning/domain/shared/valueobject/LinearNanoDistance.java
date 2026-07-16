package com.example.softlearning.domain.shared.valueobject;

public class LinearNanoDistance implements Comparable<LinearNanoDistance> {

    private final long value;
    private final LinearNanoDistanceUnit unit;

    private LinearNanoDistance(long value, LinearNanoDistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static LinearNanoDistance of(long value, LinearNanoDistanceUnit unit) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("Distance value cannot be negative");
        }
        return new LinearNanoDistance(value, unit);
    }

    public long getValue() {
        return value;
    }

    public long getValueTo(LinearNanoDistanceUnit targetUnit) {
        // Convert the value to the target unit
        long valueInNanometers = unit.toNanometers(value);
        return targetUnit.fromNanometers(valueInNanometers);
    }

    public LinearNanoDistance toUnit(LinearNanoDistanceUnit targetUnit) {
        long valueInNanometers = unit.toNanometers(value);
        long convertedValue = targetUnit.fromNanometers(valueInNanometers);
        return new LinearNanoDistance(convertedValue, targetUnit);
    }

    public LinearNanoDistance add(LinearNanoDistance other) {
        long thisInNanometers = unit.toNanometers(value);
        long otherInNanometers = other.unit.toNanometers(other.value);
        long sumInNanometers = thisInNanometers + otherInNanometers;
        long sumInOriginalUnit = unit.fromNanometers(sumInNanometers);

        return new LinearNanoDistance(sumInOriginalUnit, unit);
    }

    public LinearNanoDistance diff(LinearNanoDistance other) {
        long thisInNanometers = unit.toNanometers(value);
        long otherInNanometers = other.unit.toNanometers(other.value);
        long differenceInNanometers = thisInNanometers - otherInNanometers;
        long differenceInOriginalUnit = unit.fromNanometers(differenceInNanometers);

        return new LinearNanoDistance(differenceInOriginalUnit, unit);
    }

    public LinearNanoDistanceUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.symbol();
    }

    @Override
    public int compareTo(LinearNanoDistance other) {
        long thisInNanometers = unit.toNanometers(value);
        long otherInNanometers = other.unit.toNanometers(other.value);
        
        return Long.compare(thisInNanometers, otherInNanometers);
    }
}