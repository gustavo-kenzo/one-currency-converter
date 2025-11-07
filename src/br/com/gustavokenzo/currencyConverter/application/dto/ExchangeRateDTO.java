package br.com.gustavokenzo.currencyConverter.application.dto;

import java.util.Map;

public record ExchangeRateDTO(String result, String baseCode, Map<String, Double> conversionRates) {
}
