package com.example.softlearning.domain.shared.valueobject;

public class Weight implements Comparable<Weight> {

    private final long value;
    private final WeightUnit unit;

    private Weight(long value, WeightUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Para el contexto en que se va a desarrollar el ejemplo, se establece que el peso no puede ser cero o negativo.  
    public static Weight of(long value, WeightUnit unit) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Weight value cannot be zero or negative");
        }
        return new Weight(value, unit);
    }

    public long getValue() {
        return value;
    }

    public long getValueTo(WeightUnit targetUnit) {
        // Convert the value to the target unit
        long valueInGrams = unit.toGrams(value);
        return targetUnit.fromGrams(valueInGrams);
    }

    public Weight toUnit(WeightUnit targetUnit) {
        long valueInGrams = unit.toGrams(value);
        long convertedValue = targetUnit.fromGrams(valueInGrams);
        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight other) {
        long thisInGrams = unit.toGrams(value);
        long otherInGrams = other.unit.toGrams(other.value);
        long sumInGrams = thisInGrams + otherInGrams;
        long sumInOriginalUnit = unit.fromGrams(sumInGrams);
        return new Weight(sumInOriginalUnit, unit);
    }

    public Weight diff(Weight other) {
        long thisInGrams = unit.toGrams(value);
        long otherInGrams = other.unit.toGrams(other.value);
        long differenceInGrams = thisInGrams - otherInGrams;
        long differenceInOriginalUnit = unit.fromGrams(differenceInGrams);
        return new Weight(differenceInOriginalUnit, unit);
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.symbol();
    }

    @Override
    public int compareTo(Weight other) {
        long thisInGrams = unit.toGrams(value);
        long otherInGrams = other.unit.toGrams(other.value);
        return Long.compare(thisInGrams, otherInGrams);
    }
}