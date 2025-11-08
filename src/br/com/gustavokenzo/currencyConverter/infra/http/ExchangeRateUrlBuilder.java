package br.com.gustavokenzo.currencyConverter.infra.http;

public class ExchangeRateUrlBuilder {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;

    public ExchangeRateUrlBuilder(String apiKey) {
        this.apiKey = apiKey;
    }

    public String buildURL(String baseCurrency, String targetCurrency) {
        return BASE_URL + apiKey + "/pair/" + baseCurrency.toUpperCase() +"/"+ targetCurrency.toUpperCase();
    }
}
