package br.com.gustavokenzo.currencyConverter.infra.http;

public class ExchangeRateUrlBuilder {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static String buildURL(String apiKey, String baseCurrency) {
        return BASE_URL + apiKey + "/latest/" + baseCurrency;
    }
}
