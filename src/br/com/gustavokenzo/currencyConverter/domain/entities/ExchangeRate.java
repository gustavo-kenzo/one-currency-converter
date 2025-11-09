package br.com.gustavokenzo.currencyConverter.domain.entities;
//validacao de atributos no construtor e melhora do toString
public class ExchangeRate {
    private String baseCurrency;
    private String targetCurrency;
    private Double exchangeValue;

    public ExchangeRate(String baseCurrency, String targetCurrency, Double exchangeValue) {
        if (baseCurrency == null || baseCurrency.isBlank())
            throw new RuntimeException("Base currency can't be empty");
        if (targetCurrency == null || targetCurrency.isBlank())
            throw new RuntimeException("Target currency can't be empty");
        if (exchangeValue == null || exchangeValue <= 0)
            throw new RuntimeException("Exchange rate should be positive");

        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
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
        return baseCurrency + " → " + targetCurrency + ": " + exchangeValue + "\n";
    }
}
