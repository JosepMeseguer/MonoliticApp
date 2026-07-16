package com.example.softlearning.domain.shared.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, Currency currency) throws NullPointerException {
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(currency, "currency must not be null");

        // No redondeamos aquí: preservamos toda la precisión disponible
        return new Money(amount.stripTrailingZeros(), currency);
    }

    public static Money of(String amount, Currency currency) throws NullPointerException, NumberFormatException {
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(currency, "currency must not be null");

        // String -> BigDecimal evita problemas de precision en la construcción desde double
        return of(new BigDecimal(amount), currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    // Representación para mostrar (por ejemplo, 2 decimales para EUR/USD/GBP)
    public Money roundedForStorageOrDisplay() {
        int fractionDigits = currency.getDefaultFractionDigits(); // EUR/USD/GBP -> 2
        BigDecimal scaled = amount.setScale(fractionDigits, RoundingMode.HALF_UP);
        return new Money(scaled, currency);
    }

    // Igualdad lógica por valor, ignorando escala diferente (0.0 vs 0.00).[web:19]
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money other = (Money) o;
        return currency.equals(other.currency) && amount.compareTo(other.amount) == 0;
    }

    @Override
    public int hashCode() {
        // Usa amount.redondeado para hash estable
        return Objects.hash(currency, amount.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + amount.toPlainString();
    }

    // Ejemplos de posibles servicios a implementar: Suma de dos cantidades de dinero, asegurando que la moneda sea la misma
    public Money add(Money other) { 
        if (!this.currency.equals(other.currency))
            throw new IllegalStateException("Monedas distintas");
        return new Money(this.amount.add(other.amount), this.currency);
    }
}