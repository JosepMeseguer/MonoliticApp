package com.example.softlearning.domain.shared.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

import com.example.softlearning.domain.shared.service.port.CurrencyExchangeRate;
import com.example.softlearning.domain.shared.valueobject.Money;

public class MoneyExchange {

    private final CurrencyExchangeRate rateProvider;
    private final RoundingMode roundingMode;
    private final int targetScale;

    public MoneyExchange(CurrencyExchangeRate rateProvider, RoundingMode roundingMode, int targetScale) {
        this.rateProvider = Objects.requireNonNull(rateProvider);
        this.roundingMode = Objects.requireNonNull(roundingMode);
        this.targetScale = targetScale;
    }

    public Money convert(Money source, Currency targetCurrency) {
        if (source.getCurrency().equals(targetCurrency)) {
            return source;
        }

        BigDecimal rate = rateProvider.getRate(source.getCurrency(), targetCurrency);
        
        BigDecimal targetAmount = source.getAmount()
                                        .multiply(rate)
                                        .setScale(targetScale, roundingMode);

        return Money.of(targetAmount, targetCurrency);
    }
}