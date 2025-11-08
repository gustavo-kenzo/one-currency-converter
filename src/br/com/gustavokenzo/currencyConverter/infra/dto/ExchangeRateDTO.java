package br.com.gustavokenzo.currencyConverter.infra.dto;

public record ExchangeRateDTO(String result, String baseCode, String targetCode, Double conversionRate) {
}
