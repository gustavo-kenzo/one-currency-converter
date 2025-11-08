package br.com.gustavokenzo.currencyConverter.application;

import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.dto.ExchangeRateDTO;
import br.com.gustavokenzo.currencyConverter.infra.http.ExchangeRateUrlBuilder;
import br.com.gustavokenzo.currencyConverter.infra.http.HttpClientAdapter;
import br.com.gustavokenzo.currencyConverter.infra.services.ExchangeRateMapper;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClientAdapter client = new HttpClientAdapter();
        ExchangeRateUrlBuilder urlBuilder = new ExchangeRateUrlBuilder("933f32e3d41a0cd24f3cc88d");
        ExchangeRateMapper mapper = new ExchangeRateMapper(client);

        String json = client.get(urlBuilder.buildURL("usd","brl"));
        System.out.println(mapper.getExchange(json));
    }
}
