package com.example.softlearning.domain.shared.service.port;

import java.math.BigDecimal;
import java.util.Currency;

// puerto al servicio externo que nos permitirá obtener la tasa de cambio entre dos monedas.
public interface CurrencyExchangeRate {
    BigDecimal getRate(Currency from, Currency to);
}