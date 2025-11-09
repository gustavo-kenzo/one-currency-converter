package br.com.gustavokenzo.currencyConverter.domain.services;

import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.http.ExchangeRateUrlBuilder;
import br.com.gustavokenzo.currencyConverter.infra.http.HttpClientAdapter;
import br.com.gustavokenzo.currencyConverter.infra.services.ExchangeRateMapper;

import java.io.IOException;

public class ExchangeRateService {
    private final HttpClientAdapter client;
    private final ExchangeRateUrlBuilder urlBuilder;
    private final ExchangeRateMapper mapper;

    public ExchangeRateService(String apiKey) {
        if (apiKey == null || apiKey.isBlank())
            throw new RuntimeException("API Key should not be empty");

        this.client = new HttpClientAdapter();
        this.urlBuilder = new ExchangeRateUrlBuilder(apiKey);
        this.mapper = new ExchangeRateMapper();
    }

    public ExchangeRate searchExchange(String baseCurrency, String targetCurrency) {
        validateCurrency(baseCurrency, "Base Currency");
        validateCurrency(targetCurrency, "Target Currency");

        try {
            String url = urlBuilder.buildURL(baseCurrency, targetCurrency);
            String jsonResponse = client.get(url);
            if (jsonResponse == null || jsonResponse.isBlank())
                throw new RuntimeException("Empty response from the API");
            ExchangeRate exchangeRate = mapper.getExchange(jsonResponse);
            return exchangeRate;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error connecting to exchange rate service");
        }
    }

    public double convert(String baseCurrency, String targetCurrency, double amount) {
        if (amount <= 0)
            throw new RuntimeException("Amount must be positive. Received amount: " + amount);

        double exchangeRate = searchExchange(baseCurrency, targetCurrency).getExchangeValue();
        double result = amount * exchangeRate;
        return result;
    }

    private void validateCurrency(String curreny, String fieldCurrency) {
        if (curreny == null || curreny.isBlank())
            throw new RuntimeException(fieldCurrency + " can't be empty");
    }
}
