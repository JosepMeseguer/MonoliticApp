package com.example.softlearning.domain.shared.valueobject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class DateRange {

    private final LocalDate start;
    private final LocalDate end;

    private DateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("start y end no pueden ser null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start no puede ser posterior a end");
        }
        this.start = start;
        this.end = end;
    }

    // Factory estático
    public static DateRange of(LocalDate start, LocalDate end) throws IllegalArgumentException {
        return new DateRange(start, end);
    }

    public LocalDate start() {
        return start;
    }

    public LocalDate end() {
        return end;
    }

    // Igualdad por rango (start y end)
    public boolean sameRange(DateRange other) {
        if (other == null) return false;
        return this.start.isEqual(other.start) && this.end.isEqual(other.end);
    }

    // ¿Se solapan dos rangos (inclusive)?   Fórmula: !(end1 < start2 || start1 > end2)
    public boolean overlaps(DateRange other) {
        Objects.requireNonNull(other);
        return !(this.end.isBefore(other.start) || this.start.isAfter(other.end));
    }

    // ¿Este rango está totalmente dentro del otro?
    public boolean isWithin(DateRange other) {
        Objects.requireNonNull(other);
        return !this.start.isBefore(other.start) && !this.end.isAfter(other.end);
    }

    // ¿El otro rango está totalmente dentro de este?
    public boolean contains(DateRange other) {
        Objects.requireNonNull(other);
        return !other.start.isBefore(this.start) && !other.end.isAfter(this.end);
    }

    /**
     * Distancia en días entre el final de ESTE rango y el inicio del OTRO.
     * Puede ser:
     *   > 0  → el otro rango empieza después de que este termina (hay hueco).
     *   = 0  → el otro empieza el mismo día que este termina (rangos “tocan”).
     *   < 0  → el otro empieza antes de que este termine (solapados).
     */
    public long daysToStartOf(DateRange other) {
        Objects.requireNonNull(other);
        return ChronoUnit.DAYS.between(this.end, other.start);
    }

    @Override
    public String toString() {
        return "DateRange[" + start + " .. " + end + "]";
    }
    
    // equals/hashCode estándar apoyando el concepto de VO
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateRange)) return false;
        DateRange other = (DateRange) o;
        return start.isEqual(other.start) && end.isEqual(other.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}