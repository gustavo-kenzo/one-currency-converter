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
        this.client = new HttpClientAdapter();
        this.urlBuilder = new ExchangeRateUrlBuilder(apiKey);
        this.mapper = new ExchangeRateMapper();
    }

    public ExchangeRate buscarTaxa(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
        ExchangeRateUrlBuilder urlBuilder = new ExchangeRateUrlBuilder("933f32e3d41a0cd24f3cc88d");
        ExchangeRateMapper mapper = new ExchangeRateMapper();
        ExchangeRate exchangeRate = mapper.getExchange(client.get(urlBuilder.buildURL(baseCurrency, targetCurrency)));
        return exchangeRate;
    }

    public double convert(String baseCurrency, String targetCurrency, double amount) throws IOException, InterruptedException {
        double exchangeRate = buscarTaxa(baseCurrency, targetCurrency).getExchangeValue();
        return amount * exchangeRate;
    }
}
