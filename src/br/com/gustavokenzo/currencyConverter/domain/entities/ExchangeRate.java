package br.com.gustavokenzo.currencyConverter.domain.entities;

public class ExchangeRate {
    private String exchangeCode;
    private Double exchangeValue;

    public ExchangeRate(String exchangeCode, Double exchangeValue) {
        this.exchangeCode = exchangeCode;
        this.exchangeValue = exchangeValue;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public Double getExchangeValue() {
        return exchangeValue;
    }

    @Override
    public String toString() {
        return exchangeCode + " | " + exchangeValue + "\n";
    }
}
