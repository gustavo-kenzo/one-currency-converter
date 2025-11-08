package br.com.gustavokenzo.currencyConverter.domain.entities;

public class ExchangeRate {
    private String baseCurrency;
    private String targetCurrency;
    private Double exchangeValue;

    public ExchangeRate(String baseCurrency, String targeCurrency, Double exchangeValue) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targeCurrency;
        this.exchangeValue = exchangeValue;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargeCurrency() {
        return targetCurrency;
    }

    public Double getExchangeValue() {
        return exchangeValue;
    }

    @Override
    public String toString() {
        return baseCurrency+" | "+targetCurrency + " | " + exchangeValue + "\n";
    }
}
